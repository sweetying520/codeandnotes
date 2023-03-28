package com.dream.plugin.apm

import com.dream.asm.common.weaver.BaseWeaver
import com.dream.plugin.apm.asm.ApmClassVisitorDelegate
import com.dream.plugin.apm.extension.ApmConfig
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter

/**
 * function: 页面织入的Weaver
 *
 * @author zy
 * @since 2023/3/28
 */
class ApmWeaver(var apmConfig: ApmConfig?) : BaseWeaver() {

    override fun wrapClassWriter(classWriter: ClassWriter?): ClassVisitor {
        return ApmClassVisitorDelegate(classWriter,apmConfig)
    }
}