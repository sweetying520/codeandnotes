package com.dream.appblackandwhite.blackandwhitewidget

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.Button
import android.widget.TextView

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/12/7
 */
class GrayButton(context: Context, attrs: AttributeSet): Button(context,attrs) {

    //色彩饱和度为 0 的 Paint
    private val paint by lazy {
        val p = Paint()
        val cm = ColorMatrix()
        cm.setSaturation(0f)
        p.colorFilter = ColorMatrixColorFilter(cm)
        p
    }



    override fun draw(canvas: Canvas?) {
        canvas?.saveLayer(null,paint, Canvas.ALL_SAVE_FLAG)
        super.draw(canvas)
        canvas?.restore()
    }
}