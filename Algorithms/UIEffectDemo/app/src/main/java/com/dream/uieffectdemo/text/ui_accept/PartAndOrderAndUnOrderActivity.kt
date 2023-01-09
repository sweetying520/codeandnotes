package com.dream.uieffectdemo.text.ui_accept

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.dream.immersivestatusbar.MyBarUtils
import com.dream.uieffectdemo.R
import com.dream.uieffectdemo.text.FormatTextView
import com.dream.uieffectdemo.utils.StatusBarUtil

class PartAndOrderAndUnOrderActivity : AppCompatActivity() {

    private val toolbar by lazy {
        findViewById<Toolbar>(R.id.toolbar)
    }

    private val formatPartTv by lazy {
        findViewById<FormatTextView>(R.id.formatTextViewPart)
    }

    private val formatOrderTv by lazy {
        findViewById<FormatTextView>(R.id.formatTextViewOrder)
    }

    private val formatUnOrderTv by lazy {
        findViewById<FormatTextView>(R.id.formatTextViewUnOrder)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_part_and_order_and_un_order)
        MyBarUtils.enableTransparentStatusBar(this)
        MyBarUtils.setRootAddMarginTopWithStatusBarHeight(this)
        StatusBarUtil.StatusBarLightMode(this)
        setSupportActionBar(toolbar)

        initView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }




    private fun initView() {
        val partList = mutableListOf<String>()
        partList.add("The Material Design type scale includes a range of contrasting styles that support the needs of your product and its content.")
        partList.add("The type scale is a combination of thirteen styles that are supported by the type system. It contains reusable categories of text, each with an intended application and meaning.")
        formatPartTv.setData(partList,FormatTextView.ListType.TYPE_PART)




        val orderList = mutableListOf<String>()
        orderList.add("High contrast between thick and thin strokes")
        orderList.add("Medium-High x-height")
        orderList.add("Vertical stress in the strokes")
        orderList.add("Bracketed serifs")
        formatOrderTv.setData(orderList,FormatTextView.ListType.TYPE_ORDER)


        val unOrderList = mutableListOf<String>()
        unOrderList.add("High contrast between thick and thin strokes")
        unOrderList.add("Medium-High x-height")
        unOrderList.add("Vertical stress in the strokes")
        unOrderList.add("Bracketed serifs")
        formatUnOrderTv.setData(unOrderList,FormatTextView.ListType.TYPE_UN_ORDER)

    }
}