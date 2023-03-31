package com.dream.realinterviewquestion

import android.content.Intent
import android.os.Bundle
import android.os.Trace
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.dream.realinterviewquestion.databinding.ActivityMainBinding
import com.dream.realinterviewquestion.handle_msg_hook.HandleHookMsgActivity
import com.dream.realinterviewquestion.mvi2.NewsActivity
import com.dream.realinterviewquestion.sp_and_mmkv.SpAndMMKVActivity
import com.dream.realinterviewquestion.utils.ActivityUtils
import com.example.providerfreeinit.TestManager
import com.example.startupinit.DrawableManager

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
        startActivity(Intent(this,SecondActivity::class.java))
        Trace.endSection()
    }

    fun spAndMmkvClick(view: View) {
        startActivity(Intent(this,SpAndMMKVActivity::class.java))
    }

    fun mviClick(view: View) {
//        startActivity(Intent(this,MVIActivity::class.java))
        startActivity(Intent(this,NewsActivity::class.java))
    }

    private fun initListener() {
        mBinding.btnHMsgHook.setOnClickListener {
            ActivityUtils.startActivity<HandleHookMsgActivity>(this){
                putExtra(HandleHookMsgActivity.PARAS_1,"erdai")
                putExtra(HandleHookMsgActivity.PARAS_2,"777")
            }
        }
    }




}