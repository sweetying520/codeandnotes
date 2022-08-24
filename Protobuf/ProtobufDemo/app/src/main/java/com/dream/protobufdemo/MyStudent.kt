package com.dream.protobufdemo

/**
 * function: 对应 pb 模型实体类
 *
 * @author zy
 * @since 2022/8/24
 */
data class MyStudent(
    var name: String? = "",
    var age: Int = 0,
    var email: String? = "",
    var course: MutableList<String>? = null
)