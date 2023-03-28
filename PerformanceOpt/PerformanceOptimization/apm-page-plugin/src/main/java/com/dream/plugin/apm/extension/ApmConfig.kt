package com.dream.plugin.apm.extension

/**
 * function: 页面路径配置
 *
 * @author zy
 * @since 2023/3/24
 */
open class ApmConfig {

    //帧率，不配置则使用系统默认的：AppCompatActivity，Fragment
    var baseFrameRatePathList = mutableListOf<String>()

    //页面，不配置则使用系统默认的：AppCompatActivity，Fragment
    var basePagePathList = mutableListOf<String>()

    //启动，需进行配置，否则不会进行插桩
    var startUpAppPath: String = ""
    var startUpSplashPath: String = ""
    var startUpHomePath: String = ""

    override fun toString(): String {
        return "PagePathConfig(baseFrameRatePathList=$baseFrameRatePathList, basePagePathList=$basePagePathList, startUpAppPath='$startUpAppPath', startUpSplashPath='$startUpSplashPath', startUpHomePath='$startUpHomePath')"
    }


}