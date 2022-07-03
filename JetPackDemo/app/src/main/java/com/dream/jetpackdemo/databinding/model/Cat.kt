package com.dream.jetpackdemo.databinding.model

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/4/8
 */
data class Cat(
    //猫的名称
    var name: ObservableField<String> = ObservableField(),
    //是否显示猫的名字
    var isShowName: ObservableBoolean = ObservableBoolean()
)