package com.dream.plugin.apm.asm.mv.startup

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter

/**
 * function: showAd方法的织入
 *
 * @author zy
 * @since 2023/3/28
 */
class SplashActivityShowAdMethodVisitor(api: Int, mv: MethodVisitor?, access: Int, name: String?, desc: String?)
    : AdviceAdapter(api, mv, access, name, desc) {

    /**
     * 方法离开前调用 ApmStartUpCollection.containAd();
     */
    override fun onMethodExit(opcode: Int) {
        mv.visitMethodInsn(INVOKESTATIC, "com/dream/apm/collection/ApmStartUpCollection", "containAd", "()V", false)
    }

}