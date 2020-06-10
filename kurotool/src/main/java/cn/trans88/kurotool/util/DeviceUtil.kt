package cn.trans88.kurotool.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.TelephonyManager
import android.util.Log
import androidx.annotation.RequiresApi
import java.io.FileReader
import java.util.jar.Manifest

object DeviceUtil {
    /**
     * 获取cpu名字
     */
    fun getCpuName(): String {
        return FileReader("/proc/cpuinfo").buffered().readLine().split("[: ]".toRegex())[2]
    }

    /**
     * 获取deviceID
     * android 10 之后使用会抛异常
     */
    @SuppressLint("HardwareIds", "MissingPermission")
    fun getDeviceID(context: Context): String {
        val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val pm = context.packageManager

        if (pm.checkPermission(
                android.Manifest.permission.READ_PHONE_STATE,
                context.packageName
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            val deviceId = tm.deviceId
            Log.e("zby", "has Permission ${deviceId}")
            return deviceId
        }

        return ""
    }

    /**
     * 获得设备imei for GSM
     * android 10 之后使用会抛异常
     */
    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getImei(context: Context): String {
        val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val pm = context.packageManager

        if (pm.checkPermission(
                android.Manifest.permission.READ_PHONE_STATE,
                context.packageName
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return tm.imei
        }

        return ""
    }


    /**
     *获取电话类型 0-none 1-GSM 2-CDMA 3-SIP
     */
    fun getPhoneType(context: Context): Int {
        val tm =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return tm.phoneType
    }

    /**
     * 获取手机总内存
     */
    fun getTotalMemory():String{
        return FileReader("/proc/meminfo").buffered().readLine()
    }
}