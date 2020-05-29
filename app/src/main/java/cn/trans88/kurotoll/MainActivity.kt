package cn.trans88.kurotoll

import android.os.Build.VERSION_CODES.O
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.trans88.kurotool.net.rx.RestCreator
import cn.trans88.kurotool.net.rx.RxRestClient
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val build = RxRestClient.builder()
//            .url("http://192.168.8.66:8083/users/")
//            .build()
//            .get()
        var params =WeakHashMap<String,Any>();
//        RestCreator.getRxRestService().get("",params)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(O)
//        RxRestClient.builder()
//            .url("")
//            .build()
//            .get()
//
//        RestCreator.getRxRestService().get("",params).subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : Observer<String?> {
//                override fun onSubscribe(d: Disposable) {}
//                fun onNext(s: String?) {}
//                override fun onError(e: Throwable) {}
//                override fun onComplete() {}
//            })
//
//        RestCreator.getRxRestService().get("",params)

    }
}
