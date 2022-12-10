package com.dream.appblackandwhite

import android.content.Context
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dream.appblackandwhite.blackandwhitewidget.GrayFrameLayout

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/12/1
 */
abstract class BaseActivity: AppCompatActivity() {

    //色彩饱和度为 0 的 Paint
    private val paint by lazy {
        val p = Paint()
        val cm = ColorMatrix()
        cm.setSaturation(0f)
        p.colorFilter = ColorMatrixColorFilter(cm)
        p
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //方案二：替换内容栏 FrameLayout 为黑白化 FrameLayout
        //5.0及以上才能设置状态栏颜色
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.statusBarColor = Color.parseColor("#4A4A4A")
//        }
        //方案一：对 DecorView 进行黑白化设置
//        window.decorView.setLayerType(View.LAYER_TYPE_HARDWARE,paint)
        setContentView(getLayoutId())
        initView()
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    //方案二：替换内容栏 FrameLayout 为黑白化 FrameLayout
//    override fun onCreateView(
//        parent: View?,
//        name: String,
//        context: Context,
//        attrs: AttributeSet
//    ): View? {
//        try {
//            //tag name 为 FrameLayout
//            if ("FrameLayout" == name) {
//                val attributeCount = attrs.attributeCount
//                for (i in 0 until attributeCount) {
//                    //属性名称
//                    val attributeName = attrs.getAttributeName(i)
//                    //属性值
//                    val attributeValue = attrs.getAttributeValue(i)
//                    //属性名称为：id
//                    if ("id" == attributeName) {
//                        //@16908290 => 16908290
//                        val resId = Integer.parseInt(attributeValue.substring(1))
//                        //获取资源名称：android:id/content
//                        val idValue = resources.getResourceName(resId)
//                        if ("android:id/content" == idValue) {
//                            //如果是 DecorView 的 FrameLayout，替换为 GrayFrameLayout
//                            val grayFrameLayout = GrayFrameLayout(context, attrs)
//                            return grayFrameLayout
//                        }
//                    }
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        return super.onCreateView(parent, name, context, attrs)
//    }
}