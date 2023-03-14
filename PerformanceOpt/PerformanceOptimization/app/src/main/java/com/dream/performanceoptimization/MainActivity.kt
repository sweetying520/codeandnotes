package com.dream.performanceoptimization

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dream.performanceoptimization.anr.AnrMock

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }

    fun anrClick(view: View) {
        //1、多线程死锁
//        val anrMock = AnrMock()
//        anrMock.testAnr()
        //2、主线程耗时操作
        Thread.sleep(100000)
    }
}