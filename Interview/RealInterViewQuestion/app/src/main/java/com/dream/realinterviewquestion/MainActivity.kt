package com.dream.realinterviewquestion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.os.Trace
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.os.trace
import com.dream.realinterviewquestion.sp_and_mmkv.SpAndMMKVActivity
import com.example.providerfreeinit.TestManager
import com.example.startupinit.DrawableManager
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {

    private val mIv by lazy {
        findViewById<ImageView>(R.id.iv)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("erdai", "onCreate: MainActivity")
        Log.d("erdai", "onCreate: ${TestManager.getStr(R.string.app_name)}")
        mIv.setImageDrawable(DrawableManager.getDrawable(R.mipmap.ic_launcher))
    }

    override fun onPause() {
        super.onPause()
        Log.d("erdai", "onPause: MainActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("erdai", "onStop: MainActivity")
    }

    fun secondClick(view: View) {

        Trace.beginSection("erdai")
        Thread.sleep(500)
        startActivity(Intent(this,SecondActivity::class.java))
        Trace.endSection()
    }

    fun spAndMmkvClick(view: View) {
        startActivity(Intent(this,SpAndMMKVActivity::class.java))
    }


}