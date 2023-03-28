package com.dream.plugin.apm.asm.constant

/**
 * function: 启动织入点常量
 *
 * @author zy
 * @since 2023/3/28
 */
class StartUpWeaverPoint {

    companion object {
        /** attachBaseContext方法名 */
        const val METHOD_NAME_ATTACH_BASE_CONTEXT = "attachBaseContext"

        /** attachBaseContext的DESC */
        const val METHOD_DESC_ATTACH_BASE_CONTEXT = "(Landroid/content/Context;)V"

        /** onCreate方法名 */
        const val METHOD_NAME_ON_CREATE = "onCreate"

        /** Application的onCreate方法DESC */
        const val METHOD_DESC_APPLICATION_ON_CREATE = "()V"

        /** Activity的onCreate方法名 */
        const val METHOD_DESC_ACTIVITY_ON_CREATE = "(Landroid/os/Bundle;)V"

        /** onResume方法名 */
        const val METHOD_NAME_ON_RESUME = "onResume"

        /** Activity的onResume方法DESC */
        const val METHOD_DESC_ACTIVITY_ON_RESUME = "()V"

        /** Activity onWindowFocusChanged 方法名 */
        const val METHOD_NAME_ACTIVITY_ON_WINDOW_FOCUS_CHANGED = "onWindowFocusChanged"
        /** Application的onCreate方法DESC */
        const val METHOD_DESC_ACTIVITY_ON_WINDOW_FOCUS_CHANGED = "(Z)V"

        /** showAd 方法名 */
        const val METHOD_NAME_SHOW_AD = "showAd"

        /** showAd 方法的desc */
        const val METHOD_DESC_SHOW_AD = "()V"

    }
}