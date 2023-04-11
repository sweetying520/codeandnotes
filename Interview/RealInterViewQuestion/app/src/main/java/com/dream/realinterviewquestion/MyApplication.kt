package com.dream.realinterviewquestion

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import com.tencent.mmkv.MMKV

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/3/9
 */
class MyApplication: Application() {


    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
            private set
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Log.d("erdai", "attachBaseContext: MyApplication")
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        Log.d("erdai", "onCreate: MyApplication")

        val rootDir = MMKV.initialize(this)
        Log.d("erdai", "onCreate: $rootDir")
    }
}