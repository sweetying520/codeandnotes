package com.dream.plugin.apm.asm.mv.page

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter

/**
 * function: contentStatusViewDrawFinish方法的织入
 *
 * @author zy
 * @since 2023/3/28
 */
class ContentStatusViewDrawFinishMethodVisitor(api: Int, mv: MethodVisitor?, access: Int, name: String?, desc: String?)
    : AdviceAdapter(api, mv, access, name, desc) {

    /**
     * 方法进入前调用 ApmPageCollection.onPageFinishDraw(this);
     */
    override fun onMethodEnter() {
        super.onMethodEnter()
        mv.visitVarInsn(ALOAD, 0)
        mv.visitMethodInsn(INVOKESTATIC, "com/dream/apm/collection/ApmPageCollection", "onPageFinishDraw", "(Ljava/lang/Object;)V", false)
    }
}