package com.dream.plugin.apm

import com.dream.plugin.apm.extension.ApmConfig
import com.dream.asm.common.CommonTransform
import org.gradle.api.Project

/**
 * function: Apm页面的Transform
 *
 * @author zy
 * @since 2023/3/28
 */
class ApmTransform(project: Project, apmConfig: ApmConfig?) : CommonTransform(project) {

    init {
        this.bytecodeWeaver = ApmWeaver(apmConfig)
    }
}