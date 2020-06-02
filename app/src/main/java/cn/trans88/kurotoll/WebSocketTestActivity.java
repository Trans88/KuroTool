package cn.trans88.kurotoll;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.trans88.kurotool.net.rx.BaseObserver;
import cn.trans88.kurotool.net.webSocket.WsManager;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WebSocketTestActivity extends AppCompatActivity {
    private static final String TAG = "WebSocketTestActivity";
    TextView textView;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> testString =new ArrayList<>();
        testString.add("test send string 3");
        testString.add("test send string 4");
        WsManager wsManager = WsManager.builder()
                .wsUrl("ws://ledok.cn:8083/")//连接地址
                .heartBeatTime(10*1000)//设置心跳时间
                .reconnect(true)//设置是否重连，默认为true
                .reconnectTime(5*1000)//设置重连时间
                .heartBeatContext("beat")//设置心跳发送的内容，此内容为空时停止心跳
                .addSendString("test send string 1")//设置连接开始时发送的内容，接收String
                .addSendString("test send string 2")
                .addSendStringList(testString)//设置连接开始时发送的内容，接收List<String>
                .build();
        wsManager
                .startConnect()//开始连接
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onGot(String entities) {//接收服务器推送的内容
                        Log.e(TAG, "onGot: "+entities);
                    }
                });

        wsManager.send("test");//向服务器发送的内容


        textView=findViewById(R.id.tv_test);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = wsManager.stopConnect(1000,"normal close");
            }
        });
        button =findViewById(R.id.btn_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wsManager.send("zbyzbyzby");
            }
        });
//        Thread thread = Thread.currentThread();
//
//
//        try {
//            thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                wsManager.send("zbyzbyzby");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                wsManager.send("zbyzbyzby");
            }
        }).start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
