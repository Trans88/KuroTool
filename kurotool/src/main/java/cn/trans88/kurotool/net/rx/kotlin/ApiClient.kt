package cn.trans88.kurotool.net.rx.kotlin

import cn.trans88.kurotool.net.rx.BaseObserver
import cn.trans88.kurotool.net.rx.RxRestClient
import okhttp3.Headers
import okhttp3.HttpUrl
import okhttp3.RequestBody
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object ApiClient {
    var apiClient:RxRestClient? =null

    fun init(baseUrl: String,useInterceptor:Boolean =true){
        apiClient =RxRestClient.builder()
            .baseUrl(baseUrl)
            .useInterceptor(useInterceptor)
            .build()
    }

    suspend inline fun<reified T> post(url:String, body: RequestBody, headers: HashMap<String,String> ? =null):T =
        suspendCoroutine {
            apiClient?.let {item->
                item.setURL(url)
                    .setHeaders(headers)
                    .setBody(body)
                    .post(object : BaseObserver<T>() {
                        override fun onGot(entities: T) {
                            it.resume(entities)
                        }

                        override fun onFailed(msg: String) {
                            it.resumeWithException(Exception(msg))
                        }
                    })
            }
        }

//    suspend inline fun<reified T> post(url:String, body: RequestBody, headers: HashMap<String,String> ? =null):T{
//        return  suspendCoroutine {
//            apiClient?.let {item->
//                item.setURL(url)
//                    .setHeaders(headers)
//                    .setBody(body)
//                    .post(object : BaseObserver<T>() {
//                        override fun onGot(entities: T) {
//                            it.resume(entities)
//                        }
//
//                        override fun onFailed(msg: String) {
//                            it.resumeWithException(Exception(msg))
//                        }
//                    })
//            }
//
//        }
//    }

     suspend inline fun<reified T> get(url:String):T{
        return  suspendCoroutine {
            apiClient?.let {item->
                item.setURL(url)
                    .get(object : BaseObserver<T>() {
                        override fun onGot(entities: T) {
                            it.resume(entities)
                        }

                        override fun onFailed(msg: String) {
                            it.resumeWithException(Exception(msg))
                        }
                    })
            }

        }
    }
}