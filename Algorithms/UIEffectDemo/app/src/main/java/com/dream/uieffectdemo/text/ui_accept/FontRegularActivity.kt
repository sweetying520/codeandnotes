package com.dream.uieffectdemo.text.ui_accept

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.blankj.utilcode.util.BarUtils
import com.dream.immersivestatusbar.MyBarUtils
import com.dream.uieffectdemo.R
import com.dream.uieffectdemo.utils.StatusBarUtil

class FontRegularActivity : AppCompatActivity() {

    private val toolbar by lazy {
        findViewById<Toolbar>(R.id.toolbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_font_regular)
        MyBarUtils.enableTransparentStatusBar(this)
        MyBarUtils.setRootAddMarginTopWithStatusBarHeight(this)
        StatusBarUtil.StatusBarLightMode(this)
        setSupportActionBar(toolbar)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}


