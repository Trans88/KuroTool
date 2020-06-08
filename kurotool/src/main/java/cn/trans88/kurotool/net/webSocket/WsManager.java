package cn.trans88.kurotool.net.webSocket;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import cn.trans88.kurotool.net.rx.BaseObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
//todo 我觉得这里不用使用链式调用，可以使用接口来实现，面向接口编程
public class WsManager {
    private static final String TAG = "WsManager";

    private boolean isReconnect = true;
    private String wsUrl;
    private volatile WebSocket mWebSocket;
    private OkHttpClient okHttpClient;
    private Request mRequest;
    private Observable<String> observable;
    private volatile ObservableEmitter<String> mEmitter;

    private Integer reconnectTime;
    private Integer heartbeatTime;
    private String heartbeatContext;
    private List<String> openConnectSendStringList = new ArrayList<>();
    private List<byte[]> openConnectSendByteList =new ArrayList<>();

    private volatile boolean isCloseConnect =false;

    private volatile LinkedBlockingQueue<String> sendQueue ;
    private volatile static ThreadPoolExecutor threadPool;  //Thread pool
    private final static int poolSize = Runtime.getRuntime().availableProcessors() * 2 + 1; //Thread pool size

    private Handler wsMainHandler = new Handler(Looper.getMainLooper());




    WsManager(String wsUrl, boolean isReconnect,Integer reconnectTime,Integer heartbeatTime,String heartbeatContext,List<String> openConnectSendStringList,List<byte[]> openConnectSendByteList) {
        this.reconnectTime =reconnectTime;
        this.heartbeatTime =heartbeatTime;
        this.heartbeatContext =heartbeatContext;
        this.wsUrl = wsUrl;
        this.isReconnect = isReconnect;
        this.openConnectSendStringList =openConnectSendStringList;
        this.openConnectSendByteList =openConnectSendByteList;

        threadPool = new ThreadPoolExecutor(poolSize, poolSize, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        sendQueue =new LinkedBlockingQueue<>();
        //use producer consumer to send message and use a dead cycle to wait
        threadPool.execute(sendMessage);
        initObservable();
    }

    public static WsManagerBuilder builder() {
        return new WsManagerBuilder();
    }

    private void initObservable() {
        observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.i(TAG, "subscribe: initObservable");
                mEmitter =emitter;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    private void initWebSocket() {
        if (okHttpClient == null) {
            Log.i(TAG, "initWebSocket:okHttpClient is null ");
            okHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)//如果没配置，okhttp默认就是true
                    .build();
        }

        if (mRequest == null) {
            mRequest = new Request.Builder()
                    .url(wsUrl)
                    .build();
        }


        Log.i(TAG, "initWebSocket");
        okHttpClient.dispatcher().cancelAll();


        okHttpClient.newWebSocket(mRequest, new WebSocketListener() {
            @Override
            public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                Log.i(TAG, "onClosed connection closed : " + reason);
                super.onClosed(webSocket, code, reason);
                wsMainHandler.removeCallbacks(heartbeat);
//                tryReconnect();
            }

            @Override
            public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                //是当远程端暗示没有数据交互时回调（即此时准备关闭，但连接还没有关闭）
                Log.i(TAG, "onClosing connection closing :" + reason);
                super.onClosing(webSocket, code, reason);
            }


            @Override
            public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {

                super.onFailure(webSocket, t, response);
                Log.i(TAG, "onFailure  connection failed :" + t.getMessage());
//                mEmitter.onError(t);
                wsMainHandler.removeCallbacks(heartbeat);
                tryReconnect();
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
                Log.i(TAG, "onMessage message received: "+text);
                super.onMessage(webSocket, text);
                mEmitter.onNext(text);
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
                super.onMessage(webSocket, bytes);
            }

            @Override
            public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
                super.onOpen(webSocket, response);
                Log.i(TAG, "onOpen: connecting is open");
                mWebSocket = webSocket;
                isCloseConnect =false;

                if (heartbeatContext!=null){
                    wsMainHandler.post(heartbeat);
                }else {
                    Log.i(TAG, "No heartbeat content, do not send heartbeat");
                }


                if (openConnectSendStringList!=null&& openConnectSendStringList.size()>0){
                    for (String msg : openConnectSendStringList) {
                        mWebSocket.send(msg);
                    }
                }
            }
        });
    }

    public WsManager startConnect() {
        initWebSocket();
        return this;
    }

    public void subscribe(BaseObserver<String> observer) {
        observable.subscribe(observer);
    }

    public boolean stopConnect(Integer code,String closeContext) {
//        if (okHttpClient!=null){
//            okHttpClient.dispatcher().cancelAll();
//        }
        openConnectSendStringList.clear();

        if (mWebSocket!=null){
            isCloseConnect = mWebSocket.close(code, "closeContext");
            Log.i(TAG, "stopConnect: "+isCloseConnect );
            mWebSocket =null;
        }

        return isCloseConnect;
    }

    private void cancelReconnect() {
        wsMainHandler.removeCallbacks(reconnectRunnable);
    }

    public void send(String msg) {
        try {
            sendQueue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void tryReconnect() {
        Log.i(TAG, "tryReconnect: ");
        wsMainHandler.postDelayed(reconnectRunnable,reconnectTime);
    }

    private Runnable reconnectRunnable = () -> {
        Log.i(TAG, "Reconnecting with server......" );
        openConnectSendStringList.clear();
        initWebSocket();
    };

    private Runnable heartbeat =new Runnable() {
        @Override
        public void run() {
            mWebSocket.send(heartbeatContext);
            Log.i(TAG, "heartbeat: "+heartbeatContext );
            wsMainHandler.postDelayed(this,heartbeatTime);
        }
    };

    private Runnable sendMessage =new Runnable() {
        @Override
        public void run() {
            Log.i(TAG, "sendMessage");
            while (true){
                try {
                    while (mWebSocket==null){

                    }

                    String msg = sendQueue.take();
                    if (!isCloseConnect){
                        boolean isSend = mWebSocket.send(msg);
                        if (!isSend){
                            tryReconnect();
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
