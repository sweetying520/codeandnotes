package com.dream.jetpackdemo.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dream.jetpackdemo.R

class LiveData2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data2)

        val liveData = LiveDataBus.getInstance().with("erdai", String::class.java, false)
        liveData.observe(this){
            Log.d("erdai", "LiveData2Activity onCreate: $it")
        }
    }
}