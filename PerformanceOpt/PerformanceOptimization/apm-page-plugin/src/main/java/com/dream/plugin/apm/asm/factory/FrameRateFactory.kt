package com.dream.plugin.apm.asm.factory

import com.dream.plugin.apm.asm.constant.FrameRateWeaverPoint
import com.dream.plugin.apm.asm.mv.rate.FrameRateOnPauseMethodVisitor
import com.dream.plugin.apm.asm.mv.rate.FrameRateOnResumeMethodVisitor
import com.dream.plugin.apm.extension.ApmConfig
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter



/**
 * function: 帧率方法织入工厂
 *
 * @author zy
 * @since 2023/3/28
 */
class FrameRateFactory {
    companion object {
        /**
         * 根据className和methodName返回相应的函数织入
         */
        operator fun invoke(className: String?, api: Int, mv: MethodVisitor?, access: Int,
                            methodName: String?, desc: String?, apmConfig: ApmConfig?): MethodVisitor? {

            if(apmConfig == null || apmConfig.baseFrameRatePathList.isEmpty()){
                //外部配置统计帧率 Base 页面的 List 为空，使用系统自带的
                if (FrameRateWeaverPoint.CLASS_NAME_APP_COMPAT_ACTIVITY == className
                    || FrameRateWeaverPoint.CLASS_NAME_FRAGMENT == className) {
                    return frameRateCollect(methodName, desc, api, mv, access)
                }
            }else{
                //不为空则使用配置的
                if(apmConfig.baseFrameRatePathList.contains(className)){
                    return frameRateCollect(methodName, desc, api, mv, access)
                }
            }
            return mv
        }

        /**
         * 帧率插桩
         */
        private fun frameRateCollect(
            methodName: String?,
            desc: String?,
            api: Int,
            mv: MethodVisitor?,
            access: Int,
        ): MethodVisitor?  {
            if (FrameRateWeaverPoint.METHOD_NAME_ON_RESUME == methodName
                && FrameRateWeaverPoint.METHOD_DESC_ON_RESUME == desc
            ) {
                // OnResume执行前的织入
                return FrameRateOnResumeMethodVisitor(api, mv, access, methodName, desc)
            } else if (FrameRateWeaverPoint.METHOD_NAME_ON_PAUSE == methodName
                && FrameRateWeaverPoint.METHOD_DESC_ON_PAUSE == desc
            ) {
                // OnPause执行后的织入
                return FrameRateOnPauseMethodVisitor(api, mv, access, methodName, desc)
            }
            return mv
        }
    }
}