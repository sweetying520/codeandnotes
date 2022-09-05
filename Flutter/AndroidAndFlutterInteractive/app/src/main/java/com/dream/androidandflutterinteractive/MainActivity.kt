package com.dream.androidandflutterinteractive

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

class MainActivity : AppCompatActivity() {

    //预初始化 Flutter
    private lateinit var flutterEngine: FlutterEngine
    private lateinit var methodChannel: MethodChannel
    private val tv: TextView by lazy {
        findViewById(R.id.tv)
    }


    private var flutterFragment: FlutterFragment? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        flutterEngine = initFlutterEngine("init")
        methodChannel = MethodChannel(flutterEngine.dartExecutor,"com.dream.interactive")
        methodChannel.setMethodCallHandler { call, result ->
            if(call.method == "_incrementCounter"){
                tv.text = "接收 Flutter 发送过来的参数：${call.arguments}"
            }
        }
    }

    //1、跳转到 FlutterActivity
    fun toFlutterActivity(view: View) {
        //1、这种方式跳转非常慢
//        startActivity(
//            FlutterActivity.createDefaultIntent(this)
//        )
        //2、缓存的方式跳转非常快
        startActivity(
            FlutterActivity
                .withCachedEngine("init")
                //.backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                .build(this)
        )
    }

    //展示 FlutterFragment
    fun showFlutterFragment(view: View) {
        //执行缓存入口点
        flutterFragment = FlutterFragment
            .withCachedEngine("init")
            .shouldAttachEngineToActivity(false)
            .build()
        //执行新的入口点
//        flutterFragment = FlutterFragment
//            .withNewEngine()
//            .dartEntrypoint("newMain")
//            .build()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment_container, flutterFragment!!)
            .commit()
    }


    var count = 0
    fun sendMsgToFlutter(view: View) {
        count++
        val map = mapOf("key" to "你好，我是二代$count")
        methodChannel.invokeMethod("erdai",map,object : MethodChannel.Result{
            override fun success(result: Any?) {
                Log.d("erdai", "success: $result")
            }

            override fun error(errorCode: String, errorMessage: String?, errorDetails: Any?) {
                Log.d("erdai", "error: $errorMessage")
            }

            override fun notImplemented() {
                Log.d("erdai", "notImplemented: ")
            }
        })
    }




    private fun initFlutterEngine(@Suppress("SameParameterValue") id: String): FlutterEngine {
        val flutterEngine = FlutterEngine(this)
        flutterEngine.navigationChannel.setInitialRoute("main?{\"name\":\"345\"}")
        flutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        val flutterEngineCache = FlutterEngineCache.getInstance()
        flutterEngineCache.put(id,flutterEngine)
        return flutterEngine
    }

    override fun onDestroy() {
        super.onDestroy()
        flutterEngine.destroy()
    }


    override fun onPostResume() {
        super.onPostResume()
        flutterFragment?.onPostResume()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.apply {
            flutterFragment?.onNewIntent(this)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        flutterFragment?.onBackPressed()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        flutterFragment?.onRequestPermissionsResult(requestCode,permissions,grantResults)
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        flutterFragment?.onUserLeaveHint()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        flutterFragment?.onTrimMemory(level)
    }

}