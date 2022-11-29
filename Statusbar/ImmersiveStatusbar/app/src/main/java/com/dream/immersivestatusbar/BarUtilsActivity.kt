package com.dream.immersivestatusbar

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ScrollView
import androidx.appcompat.widget.Toolbar
import com.blankj.utilcode.util.BarUtils

class BarUtilsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_utils)

        MyBarUtils.enableTransparentStatusBar(this)
        MyBarUtils.setStatusBarColor(this,resources.getColor(R.color.color_E62117))
        MyBarUtils.setRootAddMarginTopWithStatusBarHeight(this)

//        BarUtils.setStatusBarColor(this, Color.parseColor("#E62117"))
//        val sv = findViewById<ScrollView>(R.id.sv)
//        BarUtils.addMarginTopEqualStatusBarHeight(sv)


//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowTitleEnabled(false)
//        toolbar.title = "erdai666"

    }
}