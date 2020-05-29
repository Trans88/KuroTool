package cn.trans88.kurotoll;

import android.app.Activity;
import android.database.Observable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.WeakHashMap;

import cn.trans88.kurotool.net.rx.BaseObserver;
import cn.trans88.kurotool.net.rx.EntityObserver;
import cn.trans88.kurotool.net.rx.RestCreator;
import cn.trans88.kurotool.net.rx.RxRestClient;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        RxRestClient build = RxRestClient.builder().url("").build();
        WeakHashMap<String,Object> params =new WeakHashMap<>();
//        RxRestClient.builder()
//                .baseUrl("http://192.168.8.66:8083/")
//                .url("users/")
//                .build()
//                .get()
//                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        Log.e(TAG, "onNext: "+s );
//                        Toast.makeText(TestActivity.this,s,Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, "onError: "+e.getMessage() );
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.e(TAG, "onComplete: " );
//                    }
//                });
        RxRestClient rxRestClient = RxRestClient.builder()
                .baseUrl("http://192.168.8.66:8083/") //输入基本的ip地址构建RxRestClient对象
                .useInterceptor(true)//是否开启Http拦截器，默认开启
                .build();
        rxRestClient
                .setParams("","")//输入参数，可输可不输，支持Hashmap,file,单一key-value
                .setURL("users/")//输入路由地址
                .get()//请求的方式
                .subscribeOn(Schedulers.io())//切换到IO线程
                .observeOn(AndroidSchedulers.mainThread())//切换回主线程
                .subscribe(new BaseObserver<TestEntity>() {//需要解析成的对象TestEntity可换成任意对象
                    @Override
                    protected void onGot(TestEntity entities) {
                        Log.e(TAG," getCommandId : "+entities.getCommand().getId());
                    }
                });
    }
}
