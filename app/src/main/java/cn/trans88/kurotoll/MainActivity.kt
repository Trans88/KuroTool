package cn.trans88.kurotoll

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import cn.trans88.kurotool.net.rx.BaseObserver
import cn.trans88.kurotool.net.rx.RxRestClient
import cn.trans88.kurotool.net.rx.kotlin.ApiClient
import cn.trans88.kurotool.util.DeviceUtil
import cn.trans88.kurotool.util.LogLevel
import cn.trans88.kurotool.util.LogUtil
import net.trs.kurotool.PermissionCallback
import net.trs.kurotool.PermissionX
import net.trs.kurotool.PermissionX.request

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PermissionX.request(this,Manifest.permission.READ_PHONE_STATE){allGranted,deniedList ->
            if (allGranted){
                Log.e("zby","有权限${DeviceUtil.getTotalMemory()}")

            }else{
                Log.e("zby","没有权限")
            }
        }
        LogUtil.e("asdasdasd")

//        val build = RxRestClient.builder()
//            .url("")
//            .build()
//        val result = with(build){
//            setParam("","")
//            setURL(" ")
//            get(object :BaseObserver<TestEntity>(){
//                override fun onGot(entities: TestEntity?) {
//
//                }
//            })
//
//        }
    }
}
