package com.dream.jetpackdemo.lifecycle

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/7/2
 */
class DefaultObserver: DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.d("erdai", "onCreate: DefaultObserver")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.d("erdai", "onStart: DefaultObserver")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.d("erdai", "onResume: DefaultObserver")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.d("erdai", "onPause: DefaultObserver")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.d("erdai", "onStop: DefaultObserver")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.d("erdai", "onDestroy: DefaultObserver")
    }
}