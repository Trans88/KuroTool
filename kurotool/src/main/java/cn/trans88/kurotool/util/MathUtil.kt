package cn.trans88.kurotool.util


/**
 * 取出任意数据类型最大的值
 */
fun<T:Comparable<T>> max(vararg nums:T):T{
    if (nums.isEmpty()){
        throw RuntimeException("Params can not be empty.")
    }
    var maxNum =nums[0]
    for (num in nums){
        if (num>maxNum){
            maxNum =num
        }
    }
    return maxNum
}

/**
 * 取出任意数据类型最小的值
 */
fun<T:Comparable<T>> min(vararg nums:T):T{
    if (nums.isEmpty()){
        throw RuntimeException("Params can not be empty.")
    }
    var minNum =nums[0]
    for (num in nums){
        if (num<minNum){
            minNum =num
        }
    }
    return minNum
}


/**
 * 随机指定范围内N个不重复的数
 * 最简单最基本的方法
 * @param min 指定范围最小值
 * @param max 指定范围最大值
 * @param n 随机数个数
 */
fun randomCommon(min: Int, max: Int, n: Int): IntArray? {
    if (n > max - min + 1 || max < min) {
        return null
    }
    val result = IntArray(n)
    var count = 0
    while (count < n) {
        val num = (Math.random() * (max - min)).toInt() + min
        var flag = true
        for (j in 0 until n) {
            if (num == result[j]) {
                flag = false
                break
            }
        }
        if (flag) {
            result[count] = num
            count++
        }
    }
    return result
}