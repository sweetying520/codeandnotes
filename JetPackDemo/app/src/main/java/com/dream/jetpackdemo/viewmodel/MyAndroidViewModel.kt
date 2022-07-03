package com.dream.jetpackdemo.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.dream.jetpackdemo.R

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/7/3
 */
class MyAndroidViewModel(application: Application): AndroidViewModel(application) {

    fun test(){
        val appname = getApplication<Application>().getString(R.string.app_name)
        Log.d("erdai", "test: $appname")

    }
}