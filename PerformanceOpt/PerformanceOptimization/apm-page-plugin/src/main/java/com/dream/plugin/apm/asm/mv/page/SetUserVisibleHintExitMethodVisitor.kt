package com.dream.plugin.apm.asm.mv.page

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter

/**
 * function: setUserVisibleHintExit方法的织入
 *
 * @author zy
 * @since 2023/3/28
 */
class SetUserVisibleHintExitMethodVisitor(api: Int, mv: MethodVisitor?, access: Int, name: String?, desc: String?)
    : AdviceAdapter(api, mv, access, name, desc) {

    /**
     * 方法退出前调用 ApmPageCollection.setUserVisibleHintExit(this);
     */
    override fun onMethodExit(opcode: Int) {
        super.onMethodExit(opcode)
        mv.visitVarInsn(ALOAD, 0)
        mv.visitVarInsn(ILOAD, 1)
        mv.visitMethodInsn(INVOKESTATIC, "com/dream/apm/collection/ApmPageCollection", "setUserVisibleHintExit", "(Ljava/lang/Object;Z)V", false);
    }
}