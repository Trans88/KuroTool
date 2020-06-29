package cn.trans88.kurotool.util

import android.content.Context
import android.widget.Toast
import java.time.Duration

fun String.showToast(context: Context,duration: Int =Toast.LENGTH_SHORT){
    Toast.makeText(context,this,duration).show()
}

fun Int.showToast(context: Context,duration: Int =Toast.LENGTH_SHORT){
    Toast.makeText(context,this,duration).show()
}

