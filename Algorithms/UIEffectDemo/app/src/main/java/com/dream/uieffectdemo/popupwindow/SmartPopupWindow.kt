package com.dream.uieffectdemo.popupwindow

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import com.blankj.utilcode.util.ScreenUtils
import com.dream.uieffectdemo.R

/**
 * function: 根据位置灵活弹出的 popupWindow
 *
 * @author zy
 * @since 2022/7/4
 */
@SuppressLint("InflateParams")
class SmartPopupWindow(context: Context): PopupWindow(){

    //内容 View
    private var contentRootView: View? = null
    //三角形向上的箭头
    private var ivTriangleUp: ImageView? = null
    //三角形向下的箭头
    private var ivTriangleDown: ImageView? = null
    //copy TextView
    private var tvCopy: TextView? = null
    //forward TextView
    private var tvForward: TextView? = null
    //记录锚点 View 在屏幕中的位置
    private var location: IntArray = IntArray(2)
    //复制事件
    var onCopyClickListener: (() -> Unit)? = null
    //转发事件
    var onForwardClickListener: (() -> Unit)? = null

    init {
        //1、构建 contentView
        contentRootView = LayoutInflater.from(context).inflate(R.layout.item_popupwindow_view, null)
        ivTriangleUp = contentRootView?.findViewById(R.id.iv_triangle_up)
        ivTriangleDown = contentRootView?.findViewById(R.id.iv_triangle_down)
        tvCopy = contentRootView?.findViewById(R.id.tv_copy)
        tvForward = contentRootView?.findViewById(R.id.tv_forward)
        tvCopy?.setOnClickListener {
            dismiss()
            onCopyClickListener?.invoke()
        }
        tvForward?.setOnClickListener {
            dismiss()
            onForwardClickListener?.invoke()
        }


        //2、给 PopupWindow 设置 ContentView
        contentView = contentRootView

        //3、给 PopupWindow 设置相关属性
        width = ViewGroup.LayoutParams.WRAP_CONTENT
        height = ViewGroup.LayoutParams.WRAP_CONTENT
        isFocusable = true
        isOutsideTouchable = true
        isTouchable = true
        setBackgroundDrawable(ColorDrawable())
    }

    fun showPopupWindow(anchorView: View){
        ivTriangleUp?.visibility = View.GONE
        ivTriangleDown?.visibility = View.GONE
        contentView.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED)
        anchorView.getLocationOnScreen(location)
        if(location[1] > (ScreenUtils.getAppScreenHeight() / 2 + 100)){
            //锚点 View 大于屏幕的一半 + 100（100是算上状态栏和标题栏高度的一个估值）
            ivTriangleUp?.visibility = View.GONE
            ivTriangleDown?.visibility = View.VISIBLE
            ivTriangleDown?.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED)
            //显示在锚点 View 上方的中间位置
            val x = location[0] + anchorView.width / 2 - contentView.measuredWidth / 2
            val y = location[1] - contentView.measuredHeight - (ivTriangleDown?.measuredHeight?:0)
            showAtLocation(anchorView, Gravity.NO_GRAVITY,x,y)
        }else{
            ivTriangleUp?.visibility = View.VISIBLE
            ivTriangleDown?.visibility = View.GONE
            //显示在锚点 View 下方的中间位置
            showAsDropDown(anchorView,anchorView.width / 2 - contentView.measuredWidth / 2,0)
        }
    }
}