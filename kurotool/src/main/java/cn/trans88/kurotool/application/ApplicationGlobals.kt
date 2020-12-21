package cn.trans88.kurotool.application

import android.app.Application
import java.lang.reflect.InvocationTargetException

/**
 * 获取全局的Application
 */
object ApplicationGlobals {
    private var sApplication: Application? =null

    fun getApplication(): Application? {
        if (sApplication == null) {
            try {
                sApplication = Class.forName("android.app.ActivityThread")
                    .getMethod("currentApplication")
                    .invoke(null, null as Array<Any?>?) as Application
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }
        }
        return sApplication
    }
}