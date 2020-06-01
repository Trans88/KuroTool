package cn.trans88.kurotool.net.rx;

import android.util.Log;

import androidx.annotation.CheckResult;

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
         *getGenericSuperclass() Used to return the current Class Represented entity (Class, interface, basic type or void)
         * Direct superclass of Type。If the direct superclass is of parameterized type,
         * the returned type object must explicitly reflect the type used when declared in the source code
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
                msg = "connection error: " + err;
                break;
            case CONNECT_TIMEOUT:
                msg = "connection time out: " + err;
                break;
            case BAD_NETWORK:
                msg = "network error: " + err;
                break;
            case PARSE_ERROR:
                msg = "parsing error :" + err;
                break;
            case UNKNOWN_ERROR:
                msg = "unknown error :" + err;
                break;
            default:
                msg = "unknown error: " + err;
                break;
        }

        onFailed(msg);
    }

    protected void onFailed(String msg) {
        Log.e(TAG, "onFailed: " + msg);
    }
}
