package com.dream.plugin.apm.asm

import com.dream.plugin.apm.ApmPlugin
import com.dream.plugin.apm.asm.factory.FrameRateFactory
import com.dream.plugin.apm.asm.factory.PageMvFactory
import com.dream.plugin.apm.asm.factory.StartUpMvFactory
import com.dream.plugin.apm.extension.ApmConfig
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * function: Apm页面织入
 *
 * @author zy
 * @since 2023/3/28
 */
class ApmClassVisitorDelegate(classVisitor: ClassVisitor?,var apmConfig: ApmConfig?) : ClassVisitor(Opcodes.ASM6, classVisitor), Opcodes {

    /** 进入织入的类全限定名 */
    private var className: String? = null

    override fun visit(version: Int, access: Int, name: String?, signature: String?, superName: String?, interfaces: Array<out String>?) {
        super.visit(version, access, name, signature, superName, interfaces)
        className = name
        println("====> $className")
    }

    override fun visitMethod(access: Int, name: String?, desc: String?, signature: String?, exceptions: Array<out String>?): MethodVisitor? {
        var mv = super.visitMethod(access, name, desc, signature, exceptions)
        if (ApmPlugin.pageWeaver) {
            // 进行页面织入
            mv = PageMvFactory(className, api, mv, access, name, desc,apmConfig)
        }
        if (ApmPlugin.startUpWeaver) {
            // 进行启动织入
            mv = StartUpMvFactory(className, api, mv, access, name, desc,apmConfig)
        }
        if (ApmPlugin.frameRate) {
            // 帧率织入
            mv = FrameRateFactory(className, api, mv, access, name, desc,apmConfig)
        }
        return mv
    }

}