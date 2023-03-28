package com.dream.plugin.apm

import com.dream.plugin.apm.extension.ApmConfig
import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * function: Apm页面的插件
 *
 * @author zy
 * @since 2023/3/28
 */
class ApmPlugin : Plugin<Project> {

    /**
     * 配置信息
     */
    private var apmConfig: ApmConfig? = null

    companion object {

        /** 是否开启页面织入 */
        var pageWeaver = true

        /** 开启启动织入*/
        var startUpWeaver = true

        /** 开启帧率织入 */
        var frameRate = true
    }

    /**
     * 注册Transform到构建流程
     */
    override fun apply(target: Project) {
        // 创建配置
        target.extensions.create("apmConfig", ApmConfig::class.java)
        // 获取配置
        apmConfig = target.property("apmConfig") as? ApmConfig

        //注册Transform
        val appExtension = target.extensions.getByType(AppExtension::class.java)
        appExtension.registerTransform(ApmTransform(target,apmConfig))
    }
}