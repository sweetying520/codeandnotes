package com.dream.realinterviewquestion.utils

import android.content.Context
import android.content.Intent

/**
 * function: Activity 辅助工具类
 *
 * @author zy
 * @since 2023/3/31
 */
object ActivityUtils {

    inline fun <reified T> startActivity(context: Context,block: Intent.() -> Unit = {}){
        val intent = Intent(context,T::class.java)
        block(intent)
        context.startActivity(intent)
    }
}