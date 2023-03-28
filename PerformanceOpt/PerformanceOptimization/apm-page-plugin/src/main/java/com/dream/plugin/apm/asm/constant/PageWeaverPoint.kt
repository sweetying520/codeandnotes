package com.dream.plugin.apm.asm.constant

/**
 * function: 织入点配置
 *
 * @author zy
 * @since 2023/3/28
 */
class PageWeaverPoint {

    /**
     * 定义织入点的常量
     */
    companion object {

        /** 进行切入的类全限定名AppCompatActivity */
        const val CLASS_NAME_APP_COMPAT_ACTIVITY = "androidx/appcompat/app/AppCompatActivity"

        /** 进行切入的类全限定名Fragment */
        const val CLASS_NAME_FRAGMENT = "androidx/fragment/app/Fragment"

        /** 切入方法名onCreate */
        const val METHOD_NAME_ON_CREATE = "onCreate"

        /** onCreate方法的desc */
        const val METHOD_DESC_ON_CREATE = "(Landroid/os/Bundle;)V"

        /** 切入方法名 setContentView */
        const val METHOD_NAME_SET_CONTENT_VIEW = "setContentView"

        /** setContentView方法的desc */
        const val METHOD_DESC_SET_CONTENT_VIEW = "(I)V"

        /** 切入方法名onCreateView */
        const val METHOD_NAME_ON_CREATE_VIEW = "onCreateView"

        /** onCreateView方法的desc */
        const val METHOD_DESC_ON_CREATE_VIEW = "(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;"

        /** 切入方法名onStart */
        const val METHOD_NAME_ON_START = "onStart"

        /** onStart方法的desc */
        const val METHOD_DESC_ON_START = "()V"

        /** 切入方法名onResume */
        const val METHOD_NAME_ON_RESUME = "onResume"

        /** onResume方法的desc */
        const val METHOD_DESC_ON_RESUME = "()V"

        /** 切入方法名showContentViewInvoke */
        const val METHOD_NAME_SHOW_CONTENT_VIEW_INVOKE = "showContentViewInvoke"

        /** showContentViewInvoke方法的desc */
        const val METHOD_NAME_SHOW_CONTENT_VIEW_DESC = "(Z)V"

        /** 切入方法名contentStatusViewDrawFinish */
        const val METHOD_NAME_CONTENT_STATUS_VIEW_DRAW_FINISH = "contentStatusViewDrawFinish"

        /** contentStatusViewDrawFinish的desc */
        const val METHOD_DESC_CONTENT_STATUS_VIEW_DRAW_FINISH = "()V"

        /** 切入方法名setUserVisibleHint */
        const val METHOD_NAME_SET_USER_VISIBLE_HINT = "setUserVisibleHint"

        /** 切入方法名setUserVisibleHint的desc */
        const val METHOD_DESC_SET_USER_VISIBLE_HINT = "(Z)V"

    }
}