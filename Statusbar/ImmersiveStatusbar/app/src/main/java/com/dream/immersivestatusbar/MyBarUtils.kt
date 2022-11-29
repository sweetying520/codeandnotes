package com.dream.immersivestatusbar

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.view.WindowManager

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/29
 */
object MyBarUtils {

    private const val FAKE_VIEW_TAG = "fake_view_status"

    /**
     * 将状态栏设置为透明状态栏
     *
     * @param activity Activity
     */
    fun enableTransparentStatusBar(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            val decorView = window.decorView
            val systemUiVisibility = decorView.systemUiVisibility
            decorView.systemUiVisibility = systemUiVisibility or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    /**
     * 设置状态栏颜色
     *
     * @param activity Activity
     * @param color Int
     */
    fun setStatusBarColor(activity: Activity, color: Int) {
        if(Build.VERSION.SDK_INT < 19)return
        val parent = activity.window.findViewById<ViewGroup>(android.R.id.content)
        var fakeView = parent.findViewWithTag<View>(FAKE_VIEW_TAG)
        if (fakeView != null) {
            if (fakeView.visibility == View.GONE) {
                fakeView.visibility = View.VISIBLE
            }
            fakeView.setBackgroundColor(color)
        } else {
            fakeView = View(activity)
            fakeView.tag = FAKE_VIEW_TAG
            fakeView.setBackgroundColor(color)
            val layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                getStatusBarHeight(activity)
            )
            fakeView.layoutParams = layoutParams;
            parent.addView(fakeView)
        }
    }

    /**
     * 给 Activity 根 View 设置一个 marginTop 为状态栏的高度
     *
     * @param activity Activity
     */
    fun setRootAddMarginTopWithStatusBarHeight(activity: Activity) {
        if(Build.VERSION.SDK_INT < 19)return
        val parent = activity.window.findViewById<ViewGroup>(android.R.id.content)
        val root = parent.getChildAt(0)
        val marginLayoutParams = root.layoutParams as MarginLayoutParams
        marginLayoutParams.setMargins(
            marginLayoutParams.leftMargin,
            marginLayoutParams.topMargin + getStatusBarHeight(activity),
            marginLayoutParams.rightMargin,
            marginLayoutParams.bottomMargin
        )
    }

    /**
     * 给 View 设置一个 marginTop 为状态栏的高度
     *
     * @param view View
     */
    fun setViewAddMarginTopWithStatusBarHeight(view: View) {
        if(Build.VERSION.SDK_INT < 19)return
        val marginLayoutParams = view.layoutParams as MarginLayoutParams
        marginLayoutParams.setMargins(
            marginLayoutParams.leftMargin,
            marginLayoutParams.topMargin + getStatusBarHeight(view.context),
            marginLayoutParams.rightMargin,
            marginLayoutParams.bottomMargin
        )
    }

    /**
     * 获取状态栏高度
     *
     * @params activity Activity
     */
    fun getStatusBarHeight(context: Context): Int {
        val resources = context.resources
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return resources.getDimensionPixelSize(resourceId)
    }


}