package com.dream.jetpackdemo.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/7/2
 */
class MyDefaultObserver: LifecycleObserver {
    
    @OnLifecycleEvent(value = Lifecycle.Event.ON_CREATE)
    fun defaultCreate(){
        Log.d("erdai", "defaultCreate: ")
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun defaultStart(){
        Log.d("erdai", "defaultStart: ")
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_RESUME)
    fun defaultResume(){
        Log.d("erdai", "defaultResume: ")
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_PAUSE)
    fun defaultPause(){
        Log.d("erdai", "defaultPause: ")
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_STOP)
    fun defaultStop(){
        Log.d("erdai", "defaultStop: ")
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_DESTROY)
    fun defaultDestroy(){
        Log.d("erdai", "defaultDestroy: ")
    }
    
}