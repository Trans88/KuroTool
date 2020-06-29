package cn.trans88.kurotoll;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.WeakHashMap;

import cn.trans88.kurotool.net.rx.BaseObserver;
import cn.trans88.kurotool.net.rx.RxRestClient;
import cn.trans88.kurotool.net.rx.RxRestClientBuilder;
import cn.trans88.kurotool.util.LogLevel;
import cn.trans88.kurotool.util.LogUtil;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.INSTANCE.setShowLevel(LogLevel.Null);
        LogUtil.INSTANCE.e("zby","eeeeeeeeeeeeeeeeeeeeeeeee");
//        RxRestClient build = RxRestClient.builder().url("").build();
//        WeakHashMap<String,Object> params =new WeakHashMap<>();
//        RxRestClient rxRestClient = RxRestClient.builder()
//                .baseUrl("http://192.168.8.66:8083/") //输入基本的ip地址构建RxRestClient对象
//                .useInterceptor(true)//是否开启Http拦截器，默认开启
//                .build();
//        rxRestClient
//                .setParam("", "")//输入参数，可输可不输，支持Hashmap,file,单一key-value body和param不能同时存在
//                .setURL("users/") //输入路由地址
//                .setHeader("Authorization","test")//设置请求头
////                .setBody(RequestBody.create("", MediaType.parse("application/json;charset=UTF-8")))
//                .get(new BaseObserver<TestEntity>() {//请求方式，传入一个观察者,需要解析成的对象TestEntity可换成任意实体对象
//                    @Override
//                    protected void onGot(TestEntity entities) {
//                        Log.e(TAG," getCommandId : "+entities.getCommand().getId());
//                    }
//                });
    }
}
