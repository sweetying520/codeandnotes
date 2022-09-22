package com.dream.androidandflutterinteractive

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.StringCodec
import java.util.*
import kotlin.concurrent.timerTask


class SecondActivity : AppCompatActivity() {
    companion object{
        //缓存 Flutter 引擎的 key
        const val FLUTTER_ENGINE_ID = "default"
    }
    //Flutter 引擎
    private lateinit var flutterEngine: FlutterEngine
    //FlutterFragment
    private lateinit var flutterFragment: FlutterFragment
    private lateinit var methodChannel: MethodChannel
    private lateinit var eventSink: EventChannel.EventSink
    private var electricity = 0
    private lateinit var messageChannel: BasicMessageChannel<String>
    private lateinit var reply: BasicMessageChannel.Reply<String>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        //预初始化 Flutter 引擎
        flutterEngine = initFlutterEngine(FLUTTER_ENGINE_ID)

        //初始化 FlutterFragment
        flutterFragment = FlutterFragment
            .withCachedEngine(FLUTTER_ENGINE_ID)
            .build()

        //将 FlutterFragment 嵌入到 MainActivity 中
        supportFragmentManager.beginTransaction().replace(R.id.flFragmentContainer,flutterFragment).commit()

        methodChannel = MethodChannel(flutterEngine.dartExecutor,"com.dream.interactive")
        methodChannel.setMethodCallHandler { call, result ->
            if(call.method == "sendFinish"){
                finish()
            }
        }

//        val eventChannel = EventChannel(flutterEngine.dartExecutor,"com.dream.eventchannel")
//        eventChannel.setStreamHandler(object : EventChannel.StreamHandler {
//            override fun onListen(arguments: Any?, events: EventChannel.EventSink) {
//                Log.d("erdai", "onListen: $arguments")
//                eventSink = events
//                startTimer()
//            }
//
//            override fun onCancel(arguments: Any?) {
//                Log.d("erdai", "onCancel: 断开连接")
//            }
//        })

        messageChannel = BasicMessageChannel(flutterEngine.dartExecutor,"com.dream.messagechannel",StringCodec.INSTANCE)
        messageChannel.setMessageHandler { replay: String?, reply: BasicMessageChannel.Reply<String> ->
            Log.d("erdai", "onCreate: $replay")
            Toast.makeText(this,replay,Toast.LENGTH_SHORT).show()
            reply.reply("梧桐山")
        }
        messageChannel.send("周末去爬山吗?") { replay: String? ->
            Log.d("erdai", "onCreate: $replay")
            Toast.makeText(this,replay,Toast.LENGTH_SHORT).show()
        }
    }

    private fun startTimer() {
        Timer().schedule(timerTask {
            runOnUiThread {
                electricity += 20
                eventSink.success("电量：$electricity%")
                if(electricity == 100){
                    eventSink.endOfStream()
                }
            }
        }, 0, 1000)
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

    override fun onPostResume() {
        super.onPostResume()
        flutterFragment.onPostResume()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        flutterFragment.onNewIntent(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        flutterFragment.onBackPressed()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        flutterFragment.onRequestPermissionsResult(requestCode,permissions,grantResults)
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        flutterFragment.onUserLeaveHint()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        flutterFragment.onTrimMemory(level)
    }

    override fun onDestroy() {
        super.onDestroy()
        flutterEngine.destroy()
    }
}