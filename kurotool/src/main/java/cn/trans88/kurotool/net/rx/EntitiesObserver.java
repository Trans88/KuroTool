package cn.trans88.kurotool.net.rx;


import android.util.Log;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


/**
 * Created by zby on 2019/12/23
 * 处理服务器响应
 * 从 BasicListBean 中取出 List<T>
 */

public abstract class EntitiesObserver<T> implements Observer<BasicListBean<T>> {

    private static final String TAG = EntitiesObserver.class.getSimpleName();

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BasicListBean<T> bean) {

        if (bean == null){
            onException(ExceptionReason.PARSE_ERROR,"bean is null");
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
        Log.e(TAG, "onError: " + e.getMessage());
        if (e instanceof HttpException) {
            // HTTP错误
            onException(ExceptionReason.BAD_NETWORK,e.getMessage());
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            // 连接错误
            onException(ExceptionReason.CONNECT_ERROR,e.getMessage());
        } else if (e instanceof InterruptedIOException) {
            // 连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT,e.getMessage());
        } else if ( e instanceof JSONException
                || e instanceof ParseException) {
            // 解析错误
            onException(ExceptionReason.PARSE_ERROR,e.getMessage());
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR,e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onGot(List<T> entities, String msg, int errCode);

    private void onException(ExceptionReason reason, String errerMsg) {
        String msg;
        switch (reason) {
            case CONNECT_ERROR:
                msg = "连接出错";
                break;
            case CONNECT_TIMEOUT:
                msg = "连接超时";
                break;
            case BAD_NETWORK:
                msg = "网络问题";
                break;
            case PARSE_ERROR:
                msg = "解析出错";
                break;
            case UNKNOWN_ERROR:
                msg = "未知错误";
                break;
            default:
                msg = "未知错误";
                break;
        }

        onFailed(msg+", "+errerMsg);
    }

    protected void onFailed(String msg) {
        Log.e(TAG, "onFailed: " + msg);
        onFinished();
    }

    protected void onFinished(){}
}
