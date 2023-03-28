package com.dream.plugin.apm.asm.mv.startup

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter

/**
 * function: Application的onCreate的方法织入
 *
 * @author zy
 * @since 2023/3/28
 */
class AppOnCreateMethodVisitor(api: Int, mv: MethodVisitor?, access: Int, name: String?, desc: String?)
    : AdviceAdapter(api, mv, access, name, desc) {

    /**
     * 方法进入前调用 ApmStartUpCollection.appOnCreateEnter();
     */
    override fun onMethodEnter() {
        mv.visitMethodInsn(INVOKESTATIC, "com/dream/apm/collection/ApmStartUpCollection", "appOnCreateEnter", "()V", false)
    }

    /**
     * 方法进入前调用 ApmStartUpCollection.appOnCreateLeave();
     */
    override fun onMethodExit(opcode: Int) {
        mv.visitMethodInsn(INVOKESTATIC, "com/dream/apm/collection/ApmStartUpCollection", "appOnCreateLeave", "()V", false)
    }
}