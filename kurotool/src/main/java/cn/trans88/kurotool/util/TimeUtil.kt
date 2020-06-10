package cn.trans88.kurotool.util

import android.text.format.Time
import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {
    /**
     * 判断当前系统时间是否在某一天的特定时间的段内
     *
     * @param beginHour 开始的小时，例如5
     * @param beginMin 开始小时的分钟数，例如00
     * @param endHour 结束小时，例如 8
     * @param endMin 结束小时的分钟数，例如00
     * @return true表示在范围内，否则false
     */
    fun isCurrentInTimeScope(beginHour:Int,beginMin:Int,beginSecond:Int,endHour:Int,endMin:Int,endSecond:Int):Boolean{
        var result = false // 结果


        val aDayInMillis =1000*60*60*24;//一天全部毫秒
        val currentTimeMillis = System.currentTimeMillis() // 当前时间
        val now = Time()
        now.set(currentTimeMillis)
        val startTime = Time().apply {
            set(currentTimeMillis)
            hour =beginHour
            minute =beginMin
            second=beginSecond
        }

        val endTime = Time().apply {
            set(currentTimeMillis)
            hour =beginHour
            minute =beginMin
            second=beginSecond
        }

        if (!startTime.before(endTime)){
            // 跨天的特殊情况（比如22:00-8:00）
            startTime.set(startTime.toMillis(true)-aDayInMillis)
            result =!now.before(startTime) && !now.after(endTime)
            val startTimeInThisDay =Time()
            startTimeInThisDay.set(startTime.toMillis(true)+aDayInMillis)
            if (!now.before(startTimeInThisDay)){
                result =true
            }
        }else{
            // 普通情况(比如 8:00 - 14:00)
            result =!now.before(startTime) &&!now.after(endTime)
        }

        return result
    }

    fun isCurrentInTimeScope(start:Long,end:Long):Boolean{
        val beginHour = getHour(start)
        val beginMin = getMinute(start)
        val beginSecond = getSecond(start)
        val endHour = getHour(end)
        val endMin = getMinute(end)
        val endSecond = getSecond(end)
        return isCurrentInTimeScope(beginHour,beginMin,beginSecond,endHour,endMin,endSecond)
    }

    /**
     * 旧时间转化成新时间
     * @param datetime 原时间
     * @param hour 增加的小时
     * @param minute 增加的分钟
     * @param second 增加的秒钟
     * @return 新的时间
     * @throws Exception
     */
    fun getNewTime(
        datetime: String?,
        hour: Int,
        minute: Int,
        second: Int
    ): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val originalDate = formatter.parse(datetime)
        val newTime = Calendar.getInstance()
        newTime.time = originalDate
        newTime.add(Calendar.HOUR, hour)
        newTime.add(Calendar.MINUTE, minute)
        newTime.add(Calendar.SECOND, second)
        val newDate = newTime.time
        return formatter.format(newDate)
    }

    /**
     * 时间戳转换成日期格式字符串
     * @param time 精确到秒的时间戳
     * @return
     */
    fun getDateToString(time: Long): String {
        val sf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val d = Date(time)
        return sf.format(d)
    }

    /**
     * 日期格式字符串转换成时间戳
     * @param time 精确到秒的字符串
     * @return
     */
    fun getStringToDate(time: String): Long {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = simpleDateFormat.parse(time)
        return date.time
    }

    /**
     * 获得当天零时零分零秒
     * @return Calendar
     */
    fun getCurrentDay0Clock(): Calendar? {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar[Calendar.HOUR_OF_DAY] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
        return calendar
    }

    fun getHour(time: Long): Int {
        return time.toInt() / 3600
    }

    fun getMinute(time: Long): Int {
        return (time % 3600).toInt() / 60
    }

    fun getSecond(time: Long): Int {
        return (time % 3600).toInt() % 60
    }
}