package com.dream.realinterviewquestion

import android.app.Application
import android.content.Context
import android.util.Log

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/3/9
 */
class MyApplication: Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Log.d("erdai", "attachBaseContext: MyApplication")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("erdai", "onCreate: MyApplication")
    }
}