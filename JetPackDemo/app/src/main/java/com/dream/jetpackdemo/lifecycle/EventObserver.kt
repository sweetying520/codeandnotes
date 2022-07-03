package com.dream.jetpackdemo.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.math.log

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/7/2
 */
class EventObserver: LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_CREATE -> Log.d("erdai", "ON_CREATE: EventObserver")
            Lifecycle.Event.ON_START -> Log.d("erdai", "ON_START: EventObserver")
            Lifecycle.Event.ON_RESUME -> Log.d("erdai", "ON_RESUME: EventObserver")
            Lifecycle.Event.ON_PAUSE -> Log.d("erdai", "ON_PAUSE: EventObserver")
            Lifecycle.Event.ON_STOP -> Log.d("erdai", "ON_STOP: EventObserver")
            Lifecycle.Event.ON_DESTROY -> Log.d("erdai", "ON_DESTROY: EventObserver")
            else -> {}
        }
    }
}