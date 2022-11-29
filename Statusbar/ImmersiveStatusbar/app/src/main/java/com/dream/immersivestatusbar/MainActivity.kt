package com.dream.immersivestatusbar

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyBarUtils.enableTransparentStatusBar(this)
    }

    fun toFitSystemWindowsActivity(view: View) {
        startActivity(Intent(this,FitSystemWindowsActivity::class.java))
    }

    fun toBlackTecImmersiveStatusActivity(view: View) {
        startActivity(Intent(this,BlackTecImmersiveStatusActivity::class.java))
    }

    fun toBarUtilsActivity(view: View) {
        startActivity(Intent(this,BarUtilsActivity::class.java))
    }

    /**
     * 小米全面屏上有问题
     *
     * 真正的沉浸式状态栏：状态栏，导航栏，Actionbar 都隐藏掉
     */
//    override fun onWindowFocusChanged(hasFocus: Boolean) {
//        super.onWindowFocusChanged(hasFocus)
//        if(hasFocus && Build.VERSION.SDK_INT >= 19){
//            window.decorView.systemUiVisibility =
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
//                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
//                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
//                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
//                        View.SYSTEM_UI_FLAG_FULLSCREEN or
//                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//        }
//    }
}