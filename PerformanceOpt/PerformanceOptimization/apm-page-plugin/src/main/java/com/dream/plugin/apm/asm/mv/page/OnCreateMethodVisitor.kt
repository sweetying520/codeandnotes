package com.dream.plugin.apm.asm.mv.page

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter

/**
 * function: OnCreate方法的织入
 *
 * @author zy
 * @since 2023/3/28
 */
class OnCreateMethodVisitor(api: Int, mv: MethodVisitor?, access: Int,  name: String?, desc: String?)
    : AdviceAdapter(api, mv, access, name, desc) {

    /**
     * 方法进入前调用 ApmPageCollection.onCreateEnter(this);
     */
    override fun onMethodEnter() {
        super.onMethodEnter()
        mv.visitVarInsn(ALOAD, 0)
        mv.visitMethodInsn(INVOKESTATIC, "com/dream/apm/collection/ApmPageCollection", "onCreateEnter", "(Ljava/lang/Object;)V", false)
    }

    /**
     * 方法进入前调用 ApmPageCollection.onCreateExit(this);
     */
    override fun onMethodExit(opcode: Int) {
        super.onMethodExit(opcode)
        mv.visitVarInsn(ALOAD, 0)
        mv.visitMethodInsn(INVOKESTATIC, "com/dream/apm/collection/ApmPageCollection", "onCreateExit", "(Ljava/lang/Object;)V", false)
    }
}