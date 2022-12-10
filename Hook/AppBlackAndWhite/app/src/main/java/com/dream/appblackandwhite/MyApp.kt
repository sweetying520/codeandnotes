package com.dream.appblackandwhite

import android.app.Application
import com.dream.appblackandwhite.hook.GlobalGray


/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/12/2
 */
class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        //方案三：Hook WindowManagerGlobal 中 mViews 实现 App 黑白化
        GlobalGray.hook()

        //方案一：对 DecorView 进行黑白化设置
//        val paint = Paint()
//        val colorMatrix = ColorMatrix()
//        colorMatrix.setSaturation(0f)
//        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
//        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
//            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
//                val decorView = activity.window.decorView
//                decorView.setLayerType(View.LAYER_TYPE_HARDWARE, paint)
//            }
//
//            override fun onActivityStarted(activity: Activity) {
//            }
//
//            override fun onActivityResumed(activity: Activity) {
//            }
//
//            override fun onActivityPaused(activity: Activity) {
//            }
//
//            override fun onActivityStopped(activity: Activity) {
//            }
//
//            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
//            }
//
//            override fun onActivityDestroyed(activity: Activity) {
//            }
//        })

    }

}