package com.dream.uieffectdemo.text

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.fonts.FontFamily
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.blankj.utilcode.util.SizeUtils
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
    private var fontType: Int = 1

    constructor(context: Context): this(context,null)

    constructor(context: Context,attrs: AttributeSet?): this(context,attrs,0)

    constructor(context: Context,attrs: AttributeSet?,defStyleAttr: Int): super(context,attrs,defStyleAttr){
        init(context,attrs)
    }

    private fun init(context: Context,attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomText)
        fontSize = typedArray.getInt(R.styleable.CustomText_fontSize, 0)
        fontType = typedArray.getInt(R.styleable.CustomText_fontType, 1)
        typedArray.recycle()

        //1、去除字体的 Padding
        includeFontPadding = false
        //2、根据字体类型设置字体
        applyFontType(fontType)
        //3、根据字体大小：1、设置字体大小 2、设置字母间距 3、上下 padding
        applyFontSize(fontSize)
    }

    /**
     * 根据字体类型设置字体
     *
     *@param fontType 字体类型
     */
    fun applyFontType(@FontType fontType: Int) {
        when(fontType){
            1 -> {}
            2 -> typeface = ResourcesCompat.getFont(context,R.font.roboto_medium)
            3 -> typeface = ResourcesCompat.getFont(context,R.font.roboto_bold)
            else ->{}
        }
    }

    /**
     * 根据字体大小：1、设置字体大小 2、设置字母间距
     *
     * @param fontSize 字体大小
     */
    fun applyFontSize(@FontSize fontSize: Int){
        when(fontSize){
            FONT_SIZE_32 ->{
                setPadding(0,SizeUtils.dp2px(1.2f),0,SizeUtils.dp2px(1.2f))
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 32f)
                letterSpacing = 0.0062f
                setLineSpacing(SizeUtils.dp2px(2f).toFloat(),lineSpacingMultiplier)
            }
            FONT_SIZE_28 ->{
                setPadding(0,SizeUtils.dp2px(1f),0,0)
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 28f)
                letterSpacing = 0.0059f
                setLineSpacing(SizeUtils.dp2px(1f).toFloat(),lineSpacingMultiplier)
            }
            FONT_SIZE_24 ->{
                setPadding(0,SizeUtils.dp2px(1f),0,SizeUtils.dp2px(1f))
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24f)
                letterSpacing = 0f
                setLineSpacing(SizeUtils.dp2px(2f).toFloat(),lineSpacingMultiplier)
            }
            FONT_SIZE_20 ->{
                setPadding(0,SizeUtils.dp2px(0.2f),0,0)
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20f)
                letterSpacing = 0.005f
                setLineSpacing(SizeUtils.dp2px(0.3f).toFloat(),lineSpacingMultiplier)
            }
            FONT_SIZE_18 ->{
                setPadding(0,0,0,SizeUtils.dp2px(1f))
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18f)
                letterSpacing = 0.00555f
                setLineSpacing(SizeUtils.dp2px(1f).toFloat(),lineSpacingMultiplier)
            }
            FONT_SIZE_16 ->{
                setPadding(0,0,0,SizeUtils.dp2px(1f))
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16f)
                letterSpacing = 0.009f
                setLineSpacing(SizeUtils.dp2px(1f).toFloat(),lineSpacingMultiplier)
            }
            FONT_SIZE_14 ->{
                setPadding(0,SizeUtils.dp2px(1f),0,SizeUtils.dp2px(1f))
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14f)
                letterSpacing = 0.0143f
                setLineSpacing(SizeUtils.dp2px(1.4f).toFloat(),lineSpacingMultiplier)
            }
            FONT_SIZE_12 ->{
                setPadding(0,SizeUtils.dp2px(1f),0,SizeUtils.dp2px(1f))
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12f)
                letterSpacing = 0.025f
                setLineSpacing(SizeUtils.dp2px(2f).toFloat(),lineSpacingMultiplier)
            }
            FONT_SIZE_11 ->{
                setPadding(0,SizeUtils.dp2px(0.5f),0,SizeUtils.dp2px(0.5f))
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 11f)
                letterSpacing = 0.273f
                setLineSpacing(SizeUtils.dp2px(1f).toFloat(),lineSpacingMultiplier)
            }
            FONT_SIZE_10 ->{
                setPadding(0,SizeUtils.dp2px(0.5f),0,SizeUtils.dp2px(1.5f))
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10f)
                letterSpacing = 0.03f
                setLineSpacing(SizeUtils.dp2px(2.2f).toFloat(),lineSpacingMultiplier)
            }
            else -> {
                //默认 FONT_SIZE_16
                setPadding(0,0,0,SizeUtils.dp2px(1f))
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16f)
                letterSpacing = 0.009f
                setLineSpacing(SizeUtils.dp2px(1f).toFloat(),lineSpacingMultiplier)
            }

        }
    }
}