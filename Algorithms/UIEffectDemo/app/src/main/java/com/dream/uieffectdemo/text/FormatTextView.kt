package com.dream.uieffectdemo.text

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.IntDef
import com.blankj.utilcode.util.SizeUtils
import com.dream.uieffectdemo.R
import com.dream.uieffectdemo.text.FormatTextView.ListType.Companion.TYPE_ORDER
import com.dream.uieffectdemo.text.FormatTextView.ListType.Companion.TYPE_PART
import com.dream.uieffectdemo.text.FormatTextView.ListType.Companion.TYPE_UN_ORDER

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/12/15
 */
@Suppress("SameParameterValue")
class FormatTextView: LinearLayout {

    /**
     * 布局打气筒
     */
    private lateinit var inflater: LayoutInflater

    /**
     * 自定义属性：自定大小
     */
    private var fontUnit: Int = 0

    /**
     * 自定义属性：无序列表圆点颜色
     */
    private var dotColor: Int = 0

    /**
     * 自定义属性：字体颜色
     */
    private var fontColor: Int = 0

    constructor(context: Context): this(context,null)

    constructor(context: Context,attrs: AttributeSet?): this(context,attrs,0)

    constructor(context: Context,attrs: AttributeSet?,defStyleAttr: Int): super(context,attrs,defStyleAttr){
        init(context,attrs)
    }

    /**
     * 初始化
     */
    private fun init(context: Context, attrs: AttributeSet?) {
        inflater = LayoutInflater.from(context)
        orientation = VERTICAL

        //获取自定义属性
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormatTextView)
        fontUnit = typedArray.getInt(R.styleable.FormatTextView_fontUnit, 0)
        dotColor = typedArray.getColor(R.styleable.FormatTextView_dotColor,Color.parseColor("#282B2E"))
        fontColor = typedArray.getColor(R.styleable.FormatTextView_fontColor,Color.parseColor("#282B2E"))
        typedArray.recycle()
    }

    /**
     * 设置数据
     *
     * @param strList 数据集合
     * @param listType 列表类型
     */
    fun setData(strList: MutableList<String>,@ListType listType: Int){
        if(strList.isEmpty())return
        removeAllViews()
        strList.forEachIndexed { index, str ->
            val itemView = inflater.inflate(R.layout.text_view_format,this,false)
            val tvSerial = itemView.findViewById<CustomText>(R.id.tvSerial)
            val tvContent = itemView.findViewById<CustomText>(R.id.tvContent)
            tvContent.text = str

            tvSerial.applyFontSize(fontUnit)
            tvSerial.setTextColor(fontColor)
            tvContent.applyFontSize(fontUnit)
            tvContent.setTextColor(fontColor)

            when(listType){
                TYPE_PART ->{
                    tvContent.text = "${tvContent.text}\n"
                    tvSerial.visibility = View.GONE
                    setContentLeftMargin(tvContent,0f)
                }
                TYPE_ORDER ->{
                    tvSerial.text = "${index + 1}."
                    tvSerial.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null)
                    setContentLeftMargin(tvContent,8f)
                    tvSerial.visibility = View.VISIBLE
                }
                TYPE_UN_ORDER->{
                    val dotDrawable = getDotDrawable(getDotSize(fontUnit),dotColor)
                    tvSerial.setCompoundDrawablesRelativeWithIntrinsicBounds(dotDrawable,null,null,null)
                    setContentLeftMargin(tvContent,8f)
                    tvSerial.visibility = View.VISIBLE
                }
                else -> {
                    tvSerial.visibility = View.GONE
                }
            }
            addView(itemView)
        }
    }

    /**
     * 根据字体大小获取无需列表圆点大小
     *
     * @param fontUnit 字体大小
     * @return Int 圆点大小
     */
    private fun getDotSize(fontUnit: Int): Int {
        return when(fontUnit){
            FontSize.FONT_SIZE_32 -> SizeUtils.dp2px(10f)
            FontSize.FONT_SIZE_28 -> SizeUtils.dp2px(9f)
            FontSize.FONT_SIZE_24 -> SizeUtils.dp2px(8f)
            FontSize.FONT_SIZE_20 -> SizeUtils.dp2px(7f)
            FontSize.FONT_SIZE_18 -> SizeUtils.dp2px(6f)
            FontSize.FONT_SIZE_16 -> SizeUtils.dp2px(5f)
            FontSize.FONT_SIZE_14 -> SizeUtils.dp2px(4f)
            FontSize.FONT_SIZE_12 -> SizeUtils.dp2px(3f)
            FontSize.FONT_SIZE_11 -> SizeUtils.dp2px(2f)
            FontSize.FONT_SIZE_10 -> SizeUtils.dp2px(1f)
            else -> SizeUtils.dp2px(2f)
        }
    }

    /**
     * 获取无序列表圆点
     *
     * @param size 圆点大小
     * @param dotColor 圆点颜色
     * @return GradientDrawable 无序列表圆点
     */
    private fun getDotDrawable(size: Int,dotColor: Int): GradientDrawable {
        val dotDrawable = GradientDrawable()
        dotDrawable.color = ColorStateList.valueOf(dotColor)
        dotDrawable.shape = GradientDrawable.OVAL
        dotDrawable.setSize(size,size)
        return dotDrawable
    }

    /**
     * 设置内容左边距
     *
     * @param tvContent 当前 TextView
     * @param leftMargin 左边距值
     */
    private fun setContentLeftMargin(tvContent: TextView,leftMargin: Float) {
        val marginLayoutParams = tvContent.layoutParams as MarginLayoutParams
        marginLayoutParams.leftMargin = SizeUtils.dp2px(leftMargin)
        tvContent.layoutParams = marginLayoutParams
    }


    /**
     * 自定义注解，段落间距，有序列表，无序列表
     */
    @IntDef(value = [TYPE_PART, TYPE_ORDER, TYPE_UN_ORDER])
    annotation class ListType{
        companion object{
            const val TYPE_PART = 1
            const val TYPE_ORDER = 2
            const val TYPE_UN_ORDER = 3
        }
    }
}