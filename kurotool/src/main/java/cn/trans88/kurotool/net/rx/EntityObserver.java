package cn.trans88.kurotool.net.rx;


import android.util.Log;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by zby on 2019/12/23.
 * 处理服务器响应
 * 从 BasicBean 中取出 T
 */

public abstract class EntityObserver<T> implements Observer<BasicBean<T>> {

    private static final String TAG = EntityObserver.class.getSimpleName();

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BasicBean<T> bean) {
        if (bean == null){
            onException(ExceptionReason.PARSE_ERROR,"beam is null");
            return;
        }

        if (bean.code != ErrCodes.DEFAULT_ERROR){
            onGot(bean.data, bean.msg, bean.code);
            onFinished();
        } else {
            onFailed(bean.msg);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            // HTTP错误
            onException(ExceptionReason.BAD_NETWORK,e.getMessage());
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            // 连接错误
            onException(ExceptionReason.CONNECT_ERROR,e.getMessage());
        } else if (e instanceof InterruptedIOException) {
            // 连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT,e.getMessage());
        } else if (e instanceof JSONException || e instanceof ParseException) {
            // 解析错误
            onException(ExceptionReason.PARSE_ERROR,e.getMessage());
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR,e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onGot(T entity, String msg, int errCode);

    private void onException(ExceptionReason reason, String err) {
        String msg;
        switch (reason) {
            case CONNECT_ERROR:
                msg = "连接错误"+err;
                break;
            case CONNECT_TIMEOUT:
                msg = "连接超时"+err;
                break;
            case BAD_NETWORK:
                msg = "网络出错"+err;
                break;
            case PARSE_ERROR:
                msg = "解析出错"+err;
                break;
            case UNKNOWN_ERROR:
                msg = "未知错误"+err;
                break;
            default:
                msg = "未知错误"+err;
                break;
        }

        onFailed(msg);
    }

    protected void onFailed(String msg) {
        Log.e(TAG, "onFailed: " + msg);
        onFinished();
    }

    protected void onFinished(){

    }

}
