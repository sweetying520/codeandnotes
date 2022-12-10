package com.dream.appblackandwhite.hook

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.view.View

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/12/10
 */
object GlobalGray {

    fun hook(){
        //一、获取 WindowManagerGlobal 对象
        val windowManagerGlobalClass = Class.forName("android.view.WindowManagerGlobal")
        val getInstanceStaticMethod = windowManagerGlobalClass.getDeclaredMethod("getInstance")
        val windowManagerGlobal = getInstanceStaticMethod.invoke(windowManagerGlobalClass)

        //二、获取 WindowManagerGlobal 中的 mViews
        val mViewsField = windowManagerGlobalClass.getDeclaredField("mViews")
        mViewsField.isAccessible = true
        val mViews = mViewsField.get(windowManagerGlobal) as ArrayList<View>

        //三、创建代理类对象
        //创建饱和度为 0 的画笔
        val paint = Paint()
        val cm = ColorMatrix()
        cm.setSaturation(0f)
        paint.colorFilter = ColorMatrixColorFilter(cm)

        val proxyArrayList = ObservableArrayList(object : ObservableArrayList.OnListAddListener<Any>{
            override fun add(list: ArrayList<Any>, index: Int) {
                val view = list[index] as View
                view.setLayerType(View.LAYER_TYPE_HARDWARE,paint)
            }
        })
        //将原有的数据添加到代理 ArrayList
        proxyArrayList.addAll(mViews)

        //四、使用代理对象替换原始对象
        mViewsField.set(windowManagerGlobal,proxyArrayList)
    }
}