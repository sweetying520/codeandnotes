package com.dream.plugin.apm.asm.mv.rate

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter

/**
 * function: 帧率onPause方法织入
 *
 * @author zy
 * @since 2023/3/28
 */
class FrameRateOnPauseMethodVisitor(api: Int, mv: MethodVisitor?, access: Int, name: String?, desc: String?)
    : AdviceAdapter(api, mv, access, name, desc) {

    /**
     * 方法退出前调用 ApmFrameRateCollection.onPauseLeave(this);
     */
    override fun onMethodExit(opcode: Int) {
        super.onMethodExit(opcode)
        mv.visitVarInsn(ALOAD, 0)
        mv.visitMethodInsn(INVOKESTATIC, "com/dream/apm/collection/ApmFrameRateCollection", "onPauseLeave", "(Ljava/lang/Object;)V", false)
    }
}