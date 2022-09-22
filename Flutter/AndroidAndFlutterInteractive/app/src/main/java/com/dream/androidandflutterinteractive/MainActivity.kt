package com.dream.androidandflutterinteractive

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    companion object{
        //缓存 Flutter 引擎的 key
        const val FLUTTER_ENGINE_ID = "default"
    }
    //Flutter 引擎
    private lateinit var flutterEngine: FlutterEngine


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //预初始化 Flutter 引擎
        flutterEngine = initFlutterEngine(FLUTTER_ENGINE_ID)

    }

    /**
     * 初始化 Flutter 引擎
     * 上述代码一般在跳转前调用，这样可以加快我们页面的一个跳转
     */
    private fun initFlutterEngine(engineId: String): FlutterEngine {
        //创建 Flutter 引擎
        val flutterEngine = FlutterEngine(this)
        //指定要跳转的 Flutter 页面
        flutterEngine.navigationChannel.setInitialRoute("main?{\"name\":\"erdai\",\"age\":18}")
        flutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        //缓存 FlutterEngine
        val flutterEngineCache = FlutterEngineCache.getInstance()
        flutterEngineCache.put(engineId,flutterEngine)
        return flutterEngine
    }


    override fun onDestroy() {
        super.onDestroy()
        flutterEngine.destroy()
    }

    fun toSecondActivity(view: View) {
        startActivity(Intent(this,SecondActivity::class.java))
    }
}