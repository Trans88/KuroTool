package cn.trans88.kurotool.util

import android.os.Environment
import android.util.Log
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

object LogUtil {
    private var current_level = LogLevel.Null
    private var write_to_file =false
    private var TAG="LogUtil"
    private var logFile = Environment.getExternalStorageDirectory()

    /**
     * 设置显示日志的等级
     */
    fun setShowLevel(level: LogLevel){
        current_level =level
    }

    /**
     * 设置tag
     */
    fun setTag(tag: String){
        TAG =tag
    }

    /**
     * 设置是否能写入日志
     */
    fun setLogWriteEnable(boolean: Boolean):LogUtil{
        write_to_file =boolean
        return this
    }

    /**
     * 设置日志是否写入文件
     */
    fun setLogWriteFile(file: File):LogUtil{
        logFile =file
        return this
    }

    fun v(msg:String,tag:String=TAG){
        if (current_level >= LogLevel.V){
            Log.v(tag,msg)
        }
    }

    fun d(msg:String,tag:String=TAG){
        if (current_level >= LogLevel.D){
            Log.d(tag,msg)
        }
    }

    fun i(msg:String,tag:String=TAG){
        if (current_level >= LogLevel.I){
            Log.i(tag,msg)
        }
    }

    fun w(msg:String,tag:String=TAG){
        if (current_level >= LogLevel.W){
            Log.w(tag,msg)
        }
    }

    fun e(msg:String,tag:String=TAG){
        if (current_level >= LogLevel.E){
            Log.e(tag,msg)
        }
    }

    private fun writeLogToFile(logLevel: LogLevel,msg: String,tag: String = TAG){
        if (write_to_file){
            ThreadUtil.executeByPool(Runnable {
                val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS", Locale.getDefault())
                val formatString = simpleDateFormat.format(Date())
                val logMessage ="时间：$formatString,日志等级: ${logLevel.name} tag: $tag ,msg :$msg \n"

                File(logFile.path).appendText(logMessage)
            })
        }
    }

}