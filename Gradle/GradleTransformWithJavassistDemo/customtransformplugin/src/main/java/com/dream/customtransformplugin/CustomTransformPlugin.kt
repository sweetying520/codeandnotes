package com.dream.customtransformplugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class CustomTransformPlugin: Plugin<Project>{

    override fun apply(project: Project) {
        println("erdai66666")

        // 获取 Android 扩展
        val androidExtension = project.extensions.getByType(AppExtension::class.java)
        // 注册 Transform，支持额外增加依赖
        androidExtension.registerTransform(ToastTransform(project))
    }
}