package com.dream.uieffectdemo.text

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dream.uieffectdemo.R
import com.dream.uieffectdemo.text.FormatTextView.ListType.Companion.TYPE_ORDER
import com.dream.uieffectdemo.text.FormatTextView.ListType.Companion.TYPE_PART
import com.dream.uieffectdemo.text.FormatTextView.ListType.Companion.TYPE_UN_ORDER

class TextActivity : AppCompatActivity() {


    private val formatTextView by lazy {
        findViewById<FormatTextView>(R.id.formatTextView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text)


        val strList = mutableListOf<String>()
        strList.add("This promo is over. Please check")
        strList.add("Lampe Berger Cube Scented Bouquet-Lavender Fields")
        strList.add("ttrs Berger Cube Scented Bouquet-Lavender Fields")

        formatTextView.setData(strList, TYPE_UN_ORDER)
    }
}