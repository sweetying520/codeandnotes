package com.dream.uieffectdemo.text.ui_accept

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.dream.immersivestatusbar.MyBarUtils
import com.dream.uieffectdemo.R
import com.dream.uieffectdemo.utils.StatusBarUtil

class FontBoldActivity : AppCompatActivity() {

    private val toolbar by lazy {
        findViewById<Toolbar>(R.id.toolbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_font_bold)
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