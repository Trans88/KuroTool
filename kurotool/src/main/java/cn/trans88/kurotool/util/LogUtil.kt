package cn.trans88.kurotool.util

import android.util.Log

object LogUtil {
    private var current_level = LogLevel.Null
    private var TAG="LogUtil"
    fun setShowLevel(level: LogLevel){
        current_level =level
    }

    fun setTag(tag: String){
        TAG =tag
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

}