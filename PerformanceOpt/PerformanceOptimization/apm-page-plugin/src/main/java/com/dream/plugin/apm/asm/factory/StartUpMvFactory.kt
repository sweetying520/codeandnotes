package com.dream.plugin.apm.asm.factory

import com.dream.plugin.apm.asm.constant.StartUpWeaverPoint
import com.dream.plugin.apm.asm.mv.startup.*
import com.dream.plugin.apm.extension.ApmConfig
import org.objectweb.asm.MethodVisitor

/**
 * function: 启动织入MethodVisitor工厂
 *
 * @author zy
 * @since 2023/3/28
 */
class StartUpMvFactory {

    companion object {

        /**
         * 根据className和methodName返回相应的函数织入
         */
        operator fun invoke(className: String?, api: Int, mv: MethodVisitor?, access: Int,
                            methodName: String?, desc: String?, apmConfig: ApmConfig?): MethodVisitor? {

            //需外部配置在进行启动的的插桩
            apmConfig?.apply {
                if(startUpAppPath == className){
                    // MyApplication类的织入
                    if (StartUpWeaverPoint.METHOD_NAME_ATTACH_BASE_CONTEXT == methodName
                        && StartUpWeaverPoint.METHOD_DESC_ATTACH_BASE_CONTEXT == desc) {
                        // MyApplication的attachBaseContext(Context context)织入
                        return AttachBaseContextMethodVisitor(api, mv, access, methodName, desc)
                    } else if (StartUpWeaverPoint.METHOD_NAME_ON_CREATE == methodName
                        && StartUpWeaverPoint.METHOD_DESC_APPLICATION_ON_CREATE == desc) {
                        // MyApplication的onCreate()织入
                        return AppOnCreateMethodVisitor(api, mv, access, methodName, desc)
                    }
                }else if(startUpSplashPath == className){
                    // SplashActivity类的织入
                    if (StartUpWeaverPoint.METHOD_NAME_ON_CREATE == methodName
                        && StartUpWeaverPoint.METHOD_DESC_ACTIVITY_ON_CREATE == desc) {
                        // SplashActivity的onCreate(Bundle savedInstanceState)织入
                        return SplashActivityOnCreateMethodVisitor(api, mv, access, methodName, desc)
                    } else if (StartUpWeaverPoint.METHOD_NAME_SHOW_AD == methodName
                        && StartUpWeaverPoint.METHOD_DESC_SHOW_AD == desc) {
                        // SplashActivity的containAd()织入
                        return SplashActivityShowAdMethodVisitor(api, mv, access, methodName, desc)
                    }
                }else if(startUpHomePath == className){
                    // HomeActivity类的织入
                    if (StartUpWeaverPoint.METHOD_NAME_ON_CREATE == methodName
                        && StartUpWeaverPoint.METHOD_DESC_ACTIVITY_ON_CREATE == desc) {
                        // HomeActivity的onCreate(Bundle savedInstanceState)织入
                        return HomeActivityOnCreateMethodVisitor(api, mv, access, methodName, desc)
                    }else if(StartUpWeaverPoint.METHOD_NAME_ON_RESUME == methodName
                        && StartUpWeaverPoint.METHOD_DESC_ACTIVITY_ON_RESUME == desc){
                        return HomeActivityOnResumeMethodVisitor(api, mv, access, methodName, desc)
                    } else if(StartUpWeaverPoint.METHOD_NAME_ACTIVITY_ON_WINDOW_FOCUS_CHANGED == methodName
                        && StartUpWeaverPoint.METHOD_DESC_ACTIVITY_ON_WINDOW_FOCUS_CHANGED == desc){
                        return HomeActivityOnWindowFocusChange(api, mv, access, methodName, desc)
                    }
                }
            }
            return mv
        }
    }
}