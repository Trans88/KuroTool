package cn.trans88.kurotool.ext

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Preference<T>(private val context: Context, private val name:String, private val default:T, private val prefName:String ="default"):ReadWriteProperty<Any?,T>{
    private val prefs by lazy {
        context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(findProperName(property))
    }

    private fun findProperName(property: KProperty<*>) = if(name.isEmpty()) property.name else name

    private fun findPreference(key: String):T{
        with(prefs){
            return when(default){
                is Long ->getLong(key,default)
                is Int ->getInt(key,default)
                is Boolean ->getBoolean(key,default)
                is String ->getString(key,default)
                is Float ->getFloat(key,default)
                else ->throw IllegalArgumentException("Unsupported type.")
            }as T
        }
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(findProperName(property),value)
    }

    private fun putPreference(key:String,value: T){
        with(prefs.edit()){
            when(value){
                is Long ->putLong(key,value)
                is Int ->putInt(key,value)
                is Boolean ->putBoolean(key,value)
                is String ->putString(key,value)
                is Float ->putFloat(key,value)
                else ->throw IllegalArgumentException("Unsupported type.")
            }.apply()
        }
    }

}