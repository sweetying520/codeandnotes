package com.dream.jetpackdemo

/**
 * function: 测试 kotlin 中 == 和 === 区别
 *
 * @author zy
 * @since 2022/4/6
 */

fun main(){
    val count = 1
    val b = count == 1
    println(b)


    val str1 = "erdai666"
    val str2 = String("erdai666".toByteArray())
    val b1 = str1 === str2
    println(b1)
}