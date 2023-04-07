package com.dream.realinterviewquestion

import android.content.Intent
import android.os.Bundle
import android.os.Trace
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dream.base_core.HelloService
import com.dream.base_core.HelloServiceManager
import com.dream.realinterviewquestion.customdialog.MyCustomDialog
import com.dream.realinterviewquestion.databinding.ActivityMainBinding
import com.dream.realinterviewquestion.handle_msg_hook.HandleHookMsgActivity
import com.dream.realinterviewquestion.mvi2.NewsActivity
import com.dream.realinterviewquestion.sp_and_mmkv.SpAndMMKVActivity
import com.dream.realinterviewquestion.utils.ActivityUtils
import com.example.providerfreeinit.TestManager
import com.example.startupinit.DrawableManager
import java.util.ServiceLoader

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    private val mIv by lazy {
        findViewById<ImageView>(R.id.iv)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        Log.d("erdai", "onCreate: MainActivity")
        Log.d("erdai", "onCreate: ${TestManager.getStr(R.string.app_name)}")
        mIv.setImageDrawable(DrawableManager.getDrawable(R.mipmap.ic_launcher))

        initListener()
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
        startActivity(Intent(this, SecondActivity::class.java))
        Trace.endSection()
    }

    fun spAndMmkvClick(view: View) {
        startActivity(Intent(this, SpAndMMKVActivity::class.java))
    }

    fun mviClick(view: View) {
//        startActivity(Intent(this,MVIActivity::class.java))
        startActivity(Intent(this, NewsActivity::class.java))
    }

    private fun initListener() {
        mBinding.btnHMsgHook.setOnClickListener {
            ActivityUtils.startActivity<HandleHookMsgActivity>(this) {
                putExtra(HandleHookMsgActivity.PARAS_1, "erdai")
                putExtra(HandleHookMsgActivity.PARAS_2, "777")
            }
        }

        mBinding.btnSPI.setOnClickListener {
//            val helloService = ServiceLoader.load(HelloService::class.java).iterator().next()
//            val greeting = helloService.sayHello("World")
//            Toast.makeText(this, greeting, Toast.LENGTH_SHORT).show()

            while (HelloServiceManager.hasNext()) {
                Log.d("erdai", "initListener: ${HelloServiceManager.getHelloService().sayHello("World")}")
            }
        }

        mBinding.btnDialog.setOnClickListener {
            //1、No Crash
            val myCustomDialog = MyCustomDialog(this)
            myCustomDialog.show()
            myCustomDialog.setData("erdai777")

            //2、Crash：show 之前不会执行 onCreate 方法，导致 dialog 的 view 没有初始化，出现空指针
//            val myCustomDialog = MyCustomDialog(this)
//            myCustomDialog.setData("erdai777")
//            myCustomDialog.show()
        }
    }


}