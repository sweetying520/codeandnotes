package com.dream.jetpackdemo.livedata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dream.jetpackdemo.R

class LiveDataActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        textView = findViewById(R.id.tv)
        val liveData = LiveDataBus.getInstance().with("erdai", String::class.java, false)
        liveData.observe(this){
            Log.d("erdai", "onCreate: $it")
        }


        textView.setOnClickListener {
            liveData.value = "erdai666"
            startActivity(Intent(this,LiveData2Activity::class.java))
        }
    }
}