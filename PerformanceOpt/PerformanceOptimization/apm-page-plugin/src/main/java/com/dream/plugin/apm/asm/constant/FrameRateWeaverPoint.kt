package com.dream.plugin.apm.asm.constant


/**
 * function: 帧率织入点
 *
 * @author zy
 * @since 2023/3/28
 */
class FrameRateWeaverPoint {

    /**
     * 定义织入点的常量
     */
    companion object {

        /** 进行切入的类全限定名AppCompatActivity */
        const val CLASS_NAME_APP_COMPAT_ACTIVITY = "androidx/appcompat/app/AppCompatActivity"

        /** 进行切入的类全限定名Fragment */
        const val CLASS_NAME_FRAGMENT = "androidx/fragment/app/Fragment"

        /** 切入方法名onResume */
        const val METHOD_NAME_ON_RESUME = "onResume"

        /** onResume方法的desc */
        const val METHOD_DESC_ON_RESUME = "()V"

        /** 切入方法名onPause */
        const val METHOD_NAME_ON_PAUSE = "onPause"

        /** onPause方法的desc */
        const val METHOD_DESC_ON_PAUSE = "()V"

    }
}