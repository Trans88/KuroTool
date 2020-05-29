package cn.trans88.kurotool.net.rx;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class BaseObserver<T> implements Observer<String> {
    /**
     * lalalalalall
     */
    private static final String TAG = "BaseObserver";
    private Class<T> cls = null;

    protected abstract void onGot(T entities);

//    protected BaseObserver(Class<T> cls){
//        this.cls = cls;
//    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(String json) {
        if (json == null) {
            onException(ExceptionReason.PARSE_ERROR, "Requested object is null");
            return;
        }

        /**
         *getGenericSuperclass()用来返回表示当前Class 所表示的实体（类、接口、基本类型或 void）的直接超类的Type。如果这个直接超类是参数化类型的，则返回的Type对象必须明确反映在源代码中声明时使用的类型
         */



        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

//        Class<? extends BaseObserver> aClass = getClass();
//        Type genericSuperclass = aClass.getGenericSuperclass();
//        genericSuperclass.getTypeName();


        Gson gson = new Gson();

        T object = gson.fromJson(json, entityClass);

        onGot(object);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            // HTTP错误
            onException(ExceptionReason.BAD_NETWORK, e.getMessage());
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            // 连接错误
            onException(ExceptionReason.CONNECT_ERROR, e.getMessage());
        } else if (e instanceof InterruptedIOException) {
            // 连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT, e.getMessage());
        } else if (e instanceof JSONException || e instanceof ParseException) {
            // 解析错误
            onException(ExceptionReason.PARSE_ERROR, e.getMessage());
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR, e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }

    private void onException(ExceptionReason reason, String err) {
        String msg;
        switch (reason) {
            case CONNECT_ERROR:
                msg = "连接错误" + err;
                break;
            case CONNECT_TIMEOUT:
                msg = "连接超时" + err;
                break;
            case BAD_NETWORK:
                msg = "网络出错" + err;
                break;
            case PARSE_ERROR:
                msg = "解析出错" + err;
                break;
            case UNKNOWN_ERROR:
                msg = "未知错误" + err;
                break;
            default:
                msg = "未知错误" + err;
                break;
        }

        onFailed(msg);
    }

    protected void onFailed(String msg) {
        Log.e(TAG, "onFailed: " + msg);
    }
}
