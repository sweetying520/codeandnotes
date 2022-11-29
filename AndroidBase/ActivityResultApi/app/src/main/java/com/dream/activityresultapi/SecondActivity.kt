package com.dream.activityresultapi

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    private val tvSecond by lazy {
        findViewById<TextView>(R.id.tvSecond)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val data = intent.getStringExtra("key0")
        tvSecond.text = data
    }

    fun backMainActivity(view: View) {
        val backIntent = Intent()
        backIntent.putExtra("key1","你好，我是 SecondActivity")
        setResult(Activity.RESULT_OK,backIntent)
        finish()
    }

}