package com.dream.uieffectdemo.text

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.TextView
import com.dream.uieffectdemo.R
import com.dream.uieffectdemo.text.FontSize.Companion.FONT_SIZE_10
import com.dream.uieffectdemo.text.FontSize.Companion.FONT_SIZE_11
import com.dream.uieffectdemo.text.FontSize.Companion.FONT_SIZE_12
import com.dream.uieffectdemo.text.FontSize.Companion.FONT_SIZE_14
import com.dream.uieffectdemo.text.FontSize.Companion.FONT_SIZE_16
import com.dream.uieffectdemo.text.FontSize.Companion.FONT_SIZE_18
import com.dream.uieffectdemo.text.FontSize.Companion.FONT_SIZE_20
import com.dream.uieffectdemo.text.FontSize.Companion.FONT_SIZE_24
import com.dream.uieffectdemo.text.FontSize.Companion.FONT_SIZE_28
import com.dream.uieffectdemo.text.FontSize.Companion.FONT_SIZE_32

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/12/15
 */
@SuppressLint("AppCompatCustomView")
class CustomText : TextView {

    private var fontSize: Int = 0

    constructor(context: Context): this(context,null)

    constructor(context: Context,attrs: AttributeSet?): this(context,attrs,0)

    constructor(context: Context,attrs: AttributeSet?,defStyleAttr: Int): super(context,attrs,defStyleAttr){
        init(context,attrs)
    }

    private fun init(context: Context,attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomText)
        fontSize = typedArray.getInt(R.styleable.CustomText_fontSize, 0)
        typedArray.recycle()

        //1、去除字体的 Padding
        includeFontPadding = false
        //2、根据字体大小：1、设置字体大小 2、设置字母间距
        applyFontSize(fontSize)
    }

    /**
     * 根据字体大小：1、设置字体大小 2、设置字母间距
     *
     * 应用规定的字体
     */
    fun applyFontSize(@FontSize fontSize: Int){
        when(fontSize){
            FONT_SIZE_32 ->{
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 32f)
                letterSpacing = 0.0062f
            }
            FONT_SIZE_28 ->{
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 28f)
                letterSpacing = 0.0035f
            }
            FONT_SIZE_24 ->{
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24f)
                letterSpacing = 0f
            }
            FONT_SIZE_20 ->{
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20f)
                letterSpacing = 0.005f
            }
            FONT_SIZE_18 ->{
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18f)
                letterSpacing = 0.0083f
            }
            FONT_SIZE_16 ->{
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16f)
                letterSpacing = 0.0125f
            }
            FONT_SIZE_14 ->{
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14f)
                letterSpacing = 0.0178f
            }
            FONT_SIZE_12 ->{
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12f)
                letterSpacing = 0.025f
            }
            FONT_SIZE_11 ->{
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 11f)
                letterSpacing = 0.03f
            }
            FONT_SIZE_10 ->{
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10f)
                letterSpacing = 0.035f
            }

        }
    }
}