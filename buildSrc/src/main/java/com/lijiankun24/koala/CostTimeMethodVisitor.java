package com.lijiankun24.koala;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * CostTimeMethodVisitor.java
 * <p>
 * Created by lijiankun03 on 2018/7/29.
 */
class CostTimeMethodVisitor extends AdviceAdapter {

    private static final String COST_ANNOTATION_DESC = "Lcom/lijiankun24/koala/Cost;";

    private boolean isInjected = false;

    private int startTimeId = -1;

    private String className;

    private String methodName;

    private String desc;

    private int methodId;

    CostTimeMethodVisitor(int api, MethodVisitor mv, int access, String className, String methodName, String desc) {
        super(api, mv, access, methodName, desc);
        this.className = className;
        this.methodName = methodName;
        this.desc = desc;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        if (COST_ANNOTATION_DESC.equals(desc)) {
            isInjected = true;
        }
        return super.visitAnnotation(desc, visible);
    }

    @Override
    protected void onMethodEnter() {
        if (isInjected) {
            methodId = newLocal(Type.INT_TYPE);
            mv.visitMethodInsn(INVOKESTATIC, "com/lijiankun24/koala/core/MethodCache", "request", "()I", false);
            mv.visitIntInsn(ISTORE, methodId);

            startTimeId = newLocal(Type.LONG_TYPE);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            mv.visitIntInsn(LSTORE, startTimeId);
        }
    }

    @Override
    protected void onMethodExit(int opcode) {
        if (isInjected) {
            if (opcode == RETURN) {
                visitInsn(ACONST_NULL);
            } else if (opcode == ARETURN || opcode == ATHROW) {
                dup();
            } else {
                if (opcode == LRETURN || opcode == DRETURN) {
                    dup2();
                } else {
                    dup();
                }
                box(Type.getReturnType(this.methodDesc));
            }
            mv.visitLdcInsn(className);
            mv.visitLdcInsn(methodName);
            mv.visitLdcInsn(desc);
            mv.visitVarInsn(LLOAD, startTimeId);
            mv.visitVarInsn(ILOAD, methodId);
            visitMethodInsn(INVOKESTATIC, "com/lijiankun24/koala/core/MethodCache", "updateMethodInfo",
                    "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JI)V", false);

            mv.visitVarInsn(ILOAD, methodId);
            visitMethodInsn(INVOKESTATIC, "com/lijiankun24/koala/core/MethodCache",
                    "printMethodInfo", "(I)V", false);
        }
    }
}
