package com.dream.immersivestatusbar

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FitSystemWindowsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fit_system_windows)
        MyBarUtils.enableTransparentStatusBar(this)
        val button = findViewById<Button>(R.id.btn)


        MyBarUtils.setViewAddMarginTopWithStatusBarHeight(button)
//        ViewCompat.setOnApplyWindowInsetsListener(button) { v, insets ->
//            val layoutParams = v.layoutParams as FrameLayout.LayoutParams
//            layoutParams.topMargin = insets.systemWindowInsetTop
//            insets
//        }
    }
}