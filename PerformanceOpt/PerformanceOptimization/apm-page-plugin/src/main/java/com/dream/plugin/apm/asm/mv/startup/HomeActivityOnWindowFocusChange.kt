package com.dream.plugin.apm.asm.mv.startup

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter

/**
 * function: HomeActivity 的 onWindowFocusChanged 方法织入
 *
 * @author zy
 * @since 2023/3/28
 */
class HomeActivityOnWindowFocusChange(api: Int, mv: MethodVisitor?, access: Int, name: String?, desc: String?)
    : AdviceAdapter(api, mv, access, name, desc) {

    /**
     * 方法退出前调用 ApmStartUpCollection.onStartUpComplete();
     */
    override fun onMethodExit(opcode: Int) {
        mv.visitMethodInsn(INVOKESTATIC, "com/dream/apm/collection/ApmStartUpCollection", "onStartUpComplete", "()V", false)

    }
}