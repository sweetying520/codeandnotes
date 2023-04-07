package com.dream.realinterviewquestion.customdialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import com.dream.realinterviewquestion.R


/**
 * function: 自定义 Dialog
 *
 * @author zy
 * @since 2023/4/7
 */
class MyCustomDialog(context: Context): Dialog(context) {

    private var tv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_custom_my)
        tv = findViewById(R.id.tv)

       window?.apply {
           //1、设置 window 背景
           setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
           //2、设置 window 弹出方向
           attributes.gravity = Gravity.BOTTOM
           //3、设置 window 背景的不透明度。该值是一个浮点数，范围从 0 到 1。值越大，背景越暗。
           setDimAmount(0.5f)
           //4、设置 window 宽度、高度
           setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
           //5、设置 Dialog 的动画
           setWindowAnimations(R.style.DialogAnimation)
       }

    }


    fun setData(content: String){
        tv!!.text = content
    }
}