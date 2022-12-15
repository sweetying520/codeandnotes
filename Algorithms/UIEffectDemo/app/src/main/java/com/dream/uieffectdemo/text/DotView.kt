package com.dream.uieffectdemo.text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/12/15
 */
class DotView: View {

    private lateinit var paint: Paint

    constructor(context: Context): this(context,null)

    constructor(context: Context, attrs: AttributeSet?): this(context,attrs,0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context,attrs,defStyleAttr){
        init(context,attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        paint = Paint()
        paint.color = Color.parseColor("#282B2E")
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width: Int = measureDimension(10, widthMeasureSpec)
        val height: Int = measureDimension(10, heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

    private fun measureDimension(@Suppress("SameParameterValue") defaultSize: Int, measureSpec: Int): Int {
        var result: Int
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = defaultSize //UNSPECIFIED
            if (specMode == MeasureSpec.AT_MOST) {
                result = result.coerceAtMost(specSize)
            }
        }
        return result
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(5f, 5f, 5F,paint)
    }
}