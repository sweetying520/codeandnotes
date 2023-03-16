package com.dream.realinterviewquestion

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.d("erdai", "onCreate: ")

        val stackTrace = Thread.currentThread().stackTrace
        for (element in stackTrace) {
            Log.d("erdai", "onCreate: $element")
//            Log.d("erdai1", element.className + " " + element.fileName + " " + element.methodName + " " + element.lineNumber + " " + element.isNativeMethod)
        }

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