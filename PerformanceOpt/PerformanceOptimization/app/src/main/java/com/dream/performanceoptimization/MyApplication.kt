package com.dream.performanceoptimization

import android.app.Application
import android.content.Context

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/3/27
 */
class MyApplication: Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }


    override fun onCreate() {
        super.onCreate()
    }
}