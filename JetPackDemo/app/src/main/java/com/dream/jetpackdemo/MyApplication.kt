package com.dream.jetpackdemo

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/7/2
 */
class MyApplication: Application() {


    companion object {
        var context: Application? = null
        fun getContext(): Context {
            return context!!
        }

    }


    override fun onCreate() {
        super.onCreate()
        context = this
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationLifecycleObserver())
    }


    class ApplicationLifecycleObserver: LifecycleObserver{

        @OnLifecycleEvent(value = Lifecycle.Event.ON_ANY)
        fun appOnForegroundOrBackground(lifecycleOwner: LifecycleOwner,event: Lifecycle.Event){
            if(event == Lifecycle.Event.ON_START){
                Log.d("erdai", "appOnForegroundOrBackground: 当前 app 处于前台")
            }else if(event == Lifecycle.Event.ON_STOP){
                Log.d("erdai", "appOnForegroundOrBackground: 当前 app 处于后台")
            }
        }
    }
}