package cn.trans88.kurotool.util

import net.trs.kurotool.InvisibleFragment

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