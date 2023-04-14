package com.example.flutter_plugin_example

import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {

    //也可在 MainActivity 中完成方法的回调处理
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
//        val methodChanel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger,"flutter_plugin")
//        methodChanel.setMethodCallHandler { call, result ->
//            Log.d("erdai", "configureFlutterEngine: 执行了1111")
//            when (call.method) {
//                "getPlatformVersion" -> {
//                    result.success("Android ${android.os.Build.VERSION.RELEASE}")
//                }
//                "getUserName" -> {
//                    result.success("sweetying")
//                }
//                "getAge"->{
//                    result.success(28)
//                }
//                else -> {
//                    result.notImplemented()
//                }
//            }
//        }
        super.configureFlutterEngine(flutterEngine)
    }
}
