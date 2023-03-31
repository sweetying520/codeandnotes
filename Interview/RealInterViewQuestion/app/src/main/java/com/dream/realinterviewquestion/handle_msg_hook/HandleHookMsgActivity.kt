package com.dream.realinterviewquestion.handle_msg_hook

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Handler.Callback
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dream.realinterviewquestion.databinding.ActivityHandleHookMsgBinding
import java.lang.ref.WeakReference
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import kotlin.concurrent.thread

class MyCallBack(val context: Context): Callback{
    override fun handleMessage(msg: Message): Boolean {
        if(msg.what == 0x002){
            Toast.makeText(context, "bug 修复了", Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }

}

/**
 * 通过 Hook 修复 Handler 中的坏消息
 */
class HandleHookMsgActivity : AppCompatActivity() {


    companion object{
        const val PARAS_1 = "params1"
        const val PARAS_2 = "params2"
    }

    private val myHandler by lazy {
        MyHandler(this,mainLooper)
    }


    @SuppressLint("DiscouragedPrivateApi")
    private fun hook(){
        val mCallbackFiled = Handler::class.java.getDeclaredField("mCallback")
        mCallbackFiled.isAccessible = true

        //1、自定义 CallBack
        //val proxyCallBack = MyCallBack(this)
        //2、动态代理
        val proxyCallBack = Proxy.newProxyInstance(classLoader, arrayOf(Callback::class.java),object : InvocationHandler{
            override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
                val message = args?.get(0) as? Message
                if(message?.what == 0x002){
                    Toast.makeText(this@HandleHookMsgActivity, "bug 修复了2", Toast.LENGTH_SHORT).show()
                    return true
                }
                return false
            }

        })

        mCallbackFiled.set(myHandler,proxyCallBack)
    }

    private lateinit var mBinding: ActivityHandleHookMsgBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHandleHookMsgBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        resolveIntent()
        initListener()
    }


    private fun resolveIntent() {
        val p1 = intent.getStringExtra(PARAS_1)
        val p2 = intent.getStringExtra(PARAS_2)
        Toast.makeText(this, p1 + p2, Toast.LENGTH_SHORT).show()
    }


     class MyHandler(activity: AppCompatActivity,looper: Looper): Handler(looper){

         private var weakReference: WeakReference<AppCompatActivity>

         init {
             weakReference = WeakReference(activity)
         }

        override fun handleMessage(msg: Message) {
            when(msg.what){
                0x001 -> {
                    val data1 = msg.arg1
                    Toast.makeText(weakReference.get(), "$data1", Toast.LENGTH_SHORT).show()
                }
                0x002 -> {
                    val data2 = msg.arg1
                    Log.d("erdai", "handleMessage: ${data2 / 0}")
                }
            }
        }
    }

    private fun initListener() {
        mBinding.btnHandleClick1.setOnClickListener {
            thread {
                Log.d("erdai", "initListener1: ${Thread.currentThread()}")
                val msg1 = Message.obtain()
                msg1.what = 0x001
                msg1.arg1 = 666
                myHandler.sendMessage(msg1)
            }
        }

        mBinding.btnHandleClick2.setOnClickListener {
            thread {
                Log.d("erdai", "initListener2: ${Thread.currentThread()}")
                val msg1 = Message.obtain()
                msg1.what = 0x002
                msg1.arg1 = 1
                myHandler.sendMessage(msg1)
            }
        }
        hook()
    }
}