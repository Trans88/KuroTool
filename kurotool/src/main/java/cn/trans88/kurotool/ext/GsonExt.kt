package cn.trans88.kurotool.ext

import com.google.gson.Gson

inline fun<reified T> Gson.fromJson(json:String) =fromJson(json,T::class.java)