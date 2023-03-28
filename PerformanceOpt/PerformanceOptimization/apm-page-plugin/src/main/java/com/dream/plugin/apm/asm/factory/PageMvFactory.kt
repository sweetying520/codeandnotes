package com.dream.plugin.apm.asm.factory

import com.dream.plugin.apm.asm.constant.PageWeaverPoint
import com.dream.plugin.apm.asm.mv.page.*
import com.dream.plugin.apm.extension.ApmConfig
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter


/**
 * function: 页面织入的MethodVisitor工厂
 *
 * @author zy
 * @since 2023/3/28
 */
class PageMvFactory {

    companion object {

        /**
         * 根据className和methodName返回相应的函数织入
         */
        operator fun invoke(className: String?, api: Int, mv: MethodVisitor?, access: Int,
                            methodName: String?, desc: String?, apmConfig: ApmConfig?): MethodVisitor? {

            if(apmConfig == null || apmConfig.basePagePathList.isEmpty()){
                //外部配置统计 Base 页面的 List 为空，使用系统自带的
                if (PageWeaverPoint.CLASS_NAME_APP_COMPAT_ACTIVITY == className
                    || PageWeaverPoint.CLASS_NAME_FRAGMENT == className) {
                    return pageMvCollect(methodName, desc, api, mv, access)
                }
            }else{
                //不为空则使用配置的
                if(apmConfig.basePagePathList.contains(className)){
                    // 相关方法织入
                    return pageMvCollect(methodName, desc, api, mv, access)
                }
            }
            return mv
        }

        /**
         * 页面插桩
         */
        private fun pageMvCollect(
            methodName: String?,
            desc: String?,
            api: Int,
            mv: MethodVisitor?,
            access: Int,
        ): MethodVisitor? {
            if (PageWeaverPoint.METHOD_NAME_ON_CREATE == methodName
                && PageWeaverPoint.METHOD_DESC_ON_CREATE == desc
            ) {
                // onCreate(Bundle savedInstanceState)方法
                return OnCreateMethodVisitor(api, mv, access, methodName, desc)
            } else if (PageWeaverPoint.METHOD_NAME_SET_CONTENT_VIEW == methodName
                && PageWeaverPoint.METHOD_DESC_SET_CONTENT_VIEW == desc
            ) {
                return SetContentViewMethodVisitor(api, mv, access, methodName, desc)
            } else if (PageWeaverPoint.METHOD_NAME_ON_CREATE_VIEW == methodName
                && PageWeaverPoint.METHOD_DESC_ON_CREATE_VIEW == desc
            ) {
                return OnCreateViewMethodVisitor(api, mv, access, methodName, desc)
            } else if (PageWeaverPoint.METHOD_NAME_ON_START == methodName
                && PageWeaverPoint.METHOD_DESC_ON_START == desc
            ) {
                // onStart()方法
                return OnStartMethodVisitor(api, mv, access, methodName, desc)
            } else if (PageWeaverPoint.METHOD_NAME_ON_RESUME == methodName
                && PageWeaverPoint.METHOD_DESC_ON_RESUME == desc
            ) {
                // onResume()方法
                return OnResumeMethodVisitor(api, mv, access, methodName, desc)
            } else if (PageWeaverPoint.METHOD_NAME_SHOW_CONTENT_VIEW_INVOKE == methodName
                && PageWeaverPoint.METHOD_NAME_SHOW_CONTENT_VIEW_DESC == desc
            ) {
                // showContentViewInvoke()方法
                return ShowContentViewInvokeMethodVisitor(api, mv, access, methodName, desc)
            } else if (PageWeaverPoint.METHOD_NAME_CONTENT_STATUS_VIEW_DRAW_FINISH == methodName
                && PageWeaverPoint.METHOD_DESC_CONTENT_STATUS_VIEW_DRAW_FINISH == desc
            ) {
                // contentStatusViewDrawFinish()方法
                return ContentStatusViewDrawFinishMethodVisitor(api, mv, access, methodName, desc)
            } else if (PageWeaverPoint.METHOD_NAME_SET_USER_VISIBLE_HINT == methodName
                && PageWeaverPoint.METHOD_DESC_SET_USER_VISIBLE_HINT == desc
            ) {
                // fragment的setUserVisibleHint(boolean visibleToUser)方法
                return SetUserVisibleHintExitMethodVisitor(api, mv, access, methodName, desc)
            }
            return mv
        }
    }
}