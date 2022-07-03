package com.dream.jetpackdemo.lifecycle

import android.os.Bundle
import android.util.Log
import com.dream.jetpackdemo.R

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/7/2
 */
class MyLifecycleOwnerImplActivity: MyLifecycleOwnerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_lifecycle_owner_impl_activity)
        Log.d("erdai", "onCreate: ")
        lifecycle.addObserver(MyDefaultObserver())
    }

    override fun onStart() {
        super.onStart()
        Log.d("erdai", "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("erdai", "onResume: ")
    }
}