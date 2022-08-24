package com.dream.protobufdemo

import com.google.gson.Gson
import com.google.protobuf.util.JsonFormat
import erdai.StudentOuterClass

/**
 * function: 测试
 *
 * @author zy
 * @since 2022/8/24
 */


fun main(){
    val student = StudentOuterClass.Student.newBuilder()
        .setName("erdai")
        .setAge(18)
        .setEmail("erdai666@qq.com")
        .addAllCourse(mutableListOf("Math", "English", "Computer"))
        .build()
    //将 Protobuf 转换为 Json
    val json = JsonFormat.printer().print(student)

    //将 Json 转换为 Java Bean 对象
    val myStudent = Gson().fromJson(json,MyStudent::class.java)
    println(myStudent)
}