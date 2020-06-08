package cn.trans88.kurotool.util

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

object ThreadUtil {
    val poolSize =Runtime.getRuntime().availableProcessors()*2+1
    val threadpool = ThreadPoolExecutor(poolSize, poolSize,10, TimeUnit.SECONDS, LinkedBlockingQueue())

    fun executeByPool(runnable: Runnable){
        threadpool.execute(runnable)
    }

}