package com.dream.jetpackdemo.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dream.jetpackdemo.R

class LifeCycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)
        lifecycle.addObserver(MyDefaultObserver())
        Log.d("erdai", "onCreate: LifeCycleActivity")
    }

    override fun onStart() {
        super.onStart()
        Log.d("erdai", "onStart: LifeCycleActivity")
    }

    override fun onResume() {
        super.onResume()
        Log.d("erdai", "onResume: LifeCycleActivity")
    }

    override fun onPause() {
        super.onPause()
        Log.d("erdai", "onPause: LifeCycleActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("erdai", "onStop: LifeCycleActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("erdai", "onDestroy: LifeCycleActivity")
    }

}