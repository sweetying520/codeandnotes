package com.dream.plugin.apm.asm.mv.rate

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter

/**
 * function: 帧率onResume方法织入
 *
 * @author zy
 * @since 2023/3/28
 */
class FrameRateOnResumeMethodVisitor(api: Int, mv: MethodVisitor?, access: Int, name: String?, desc: String?)
    : AdviceAdapter(api, mv, access, name, desc) {

    /**
     * 方法进入前调用 ApmFrameRateCollection.onResumeEnter(this);
     */
    override fun onMethodEnter() {
        mv.visitVarInsn(ALOAD, 0)
        mv.visitMethodInsn(INVOKESTATIC, "com/dream/apm/collection/ApmFrameRateCollection", "onResumeEnter", "(Ljava/lang/Object;)V", false)
    }
}