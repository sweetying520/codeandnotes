package com.dream.plugin.apm.asm.mv.startup

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter

/**
 * function: HomeActivity的onCreate方法织入
 *
 * @author zy
 * @since 2023/3/28
 */
class HomeActivityOnCreateMethodVisitor(api: Int, mv: MethodVisitor?, access: Int, name: String?, desc: String?)
    : AdviceAdapter(api, mv, access, name, desc) {

    /**
     * 方法进入前调用 ApmStartUpCollection.homeActivityOnCreateEnter();
     */
    override fun onMethodEnter() {
        mv.visitMethodInsn(INVOKESTATIC, "com/dream/apm/collection/ApmStartUpCollection", "homeActivityOnCreateEnter", "()V", false)
    }

    /**
     * 方法进入前调用 ApmStartUpCollection.homeActivityOnCreateLeave();
     */
    override fun onMethodExit(opcode: Int) {
        mv.visitMethodInsn(INVOKESTATIC, "com/dream/apm/collection/ApmStartUpCollection", "homeActivityOnCreateLeave", "()V", false)
    }
}