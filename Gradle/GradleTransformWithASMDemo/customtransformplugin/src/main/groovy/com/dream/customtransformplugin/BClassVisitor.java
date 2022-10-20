package com.dream.customtransformplugin;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

class BClassVisitor extends ClassVisitor {

    private boolean isOpen;

    BClassVisitor(ClassVisitor nextVisitor) {
        super(Opcodes.ASM5, nextVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor visitor = super.visitMethod(access, name, descriptor, signature, exceptions);
        // AdviceAdapter 是 MethodVisitor 的子类，使用 AdviceAdapter 可以更方便的修改方法的字节码。
        // AdviceAdapter其中几个重要方法如下：
        // void visitCode()：表示 ASM 开始扫描这个方法
        // void onMethodEnter()：进入这个方法
        // void onMethodExit()：即将从这个方法出去
        // void onVisitEnd()：表示方法扫描完毕
        return new AdviceAdapter(Opcodes.ASM5, visitor, access, name, descriptor) {

            @Override
            public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
                if("Lcom/dream/customannotation/CostTime;".equals(descriptor)){
                    isOpen = true;
                }
                return super.visitAnnotation(descriptor, visible);
            }

            @Override
            protected void onMethodEnter() {
                if(!isOpen){
                    return;
                }
                visitMethodInsn(INVOKESTATIC, "android/os/SystemClock", "elapsedRealtime", "()J", false);
                visitVarInsn(LSTORE, 2);
                visitLdcInsn("erdai");
                visitTypeInsn(NEW, "java/lang/StringBuilder");
                visitInsn(DUP);
                visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
                visitLdcInsn("\u5f00\u59cb\u65f6\u95f4: ");
                visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
                visitVarInsn(LLOAD, 2);
                visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;", false);
                visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
                visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
                visitInsn(POP);
                super.onMethodEnter();
            }

            @Override
            protected void onMethodExit(int opcode) {
                super.onMethodExit(opcode);
                if(!isOpen){
                    return;
                }
                visitMethodInsn(INVOKESTATIC, "android/os/SystemClock", "elapsedRealtime", "()J", false);
                visitVarInsn(LSTORE, 4);
                visitLdcInsn("erdai");
                visitTypeInsn(NEW, "java/lang/StringBuilder");
                visitInsn(DUP);
                visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
                visitLdcInsn("\u7ed3\u675f\u65f6\u95f4: ");
                visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
                visitVarInsn(LLOAD, 4);
                visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;", false);
                visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
                visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
                visitInsn(POP);
                visitVarInsn(LLOAD, 4);
                visitVarInsn(LLOAD, 2);
                visitInsn(LSUB);
                visitVarInsn(LSTORE, 6);
                visitLdcInsn("erdai");
                visitTypeInsn(NEW, "java/lang/StringBuilder");
                visitInsn(DUP);
                visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
                visitLdcInsn("onCreate\u65b9\u6cd5\u8017\u65f6");
                visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
                visitVarInsn(LLOAD, 6);
                visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;", false);
                visitLdcInsn("\u6beb\u79d2");
                visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
                visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
                visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
                visitInsn(POP);
            }
        };
    }
}






















