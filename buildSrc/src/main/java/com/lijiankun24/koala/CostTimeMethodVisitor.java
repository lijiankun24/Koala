package com.lijiankun24.koala;

import com.lijiankun24.koala.core.MethodCache;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

import java.lang.reflect.Modifier;

/**
 * CostTimeMethodVisitor.java
 * <p>
 * Created by lijiankun03 on 2018/7/29.
 */
class CostTimeMethodVisitor extends AdviceAdapter {

    private boolean isInjected = false;

    private int startTimeId = -1;

    private String methodName;

    private int access;

    private int methodParameterCount;

    private String[] methodParametersNames;

    private int methodId;


    CostTimeMethodVisitor(int api, MethodVisitor mv, int access, String name, String desc) {
        super(api, mv, access, name, desc);
        this.methodName = name;
        this.access = access;
        methodId = MethodCache.Request();
        methodParameterCount = Type.getArgumentTypes(desc).length;
        methodParametersNames = new String[methodParameterCount];
        System.out.println("CostTimeMethodVisitor name ================ " + name);
    }

    @Override
    public void visitParameter(String name, int access) {
        super.visitParameter(name, access);
        System.out.println("visitParameter name ================ " + name);
    }

    @Override
    public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
        super.visitLocalVariable(name, desc, signature, start, end, index);
        int methodParameterIndex = Modifier.isStatic(access) ? index : (index - 1);
        if (0 <= methodParameterIndex && methodParameterIndex < methodParameterCount) {
            methodParametersNames[methodParameterIndex] = name;
        }
        System.out.println("the method's visitLocalVariable");

    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        if (Type.getDescriptor(Cost.class).equals(desc)) {
            System.out.println("the method's visitAnnotation");
            isInjected = true;
        }
        return super.visitAnnotation(desc, visible);
    }

    @Override
    protected void onMethodEnter() {
        if (isInjected) {
            System.out.println("the method's onMethodEnter");
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
            mv.visitVarInsn(LLOAD, startTimeId);
            mv.visitLdcInsn(methodId);
            visitMethodInsn(INVOKESTATIC, "com/lijiankun24/koala/core/MethodCache",
                    "updateMethodDurationAndResult", "(Ljava/lang/Object;JI)V", false);
            mv.visitLdcInsn(methodId);
            visitMethodInsn(INVOKESTATIC, "com/lijiankun24/koala/core/MethodCache",
                    "printMethodInfo", "(I)V", false);

//            for (int i = 0; i < methodParametersNames.length; i++) {
//                System.out.println("the method's " + i + " name is " + methodParametersNames[i]);
//            }
//            int durationId = newLocal(Type.LONG_TYPE);
//            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
//            mv.visitVarInsn(LLOAD, startTimeId);
//            mv.visitInsn(LSUB);
//            mv.visitVarInsn(LSTORE, durationId);
//            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
//            mv.visitInsn(DUP);
//            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
//            mv.visitLdcInsn("The cost time of " + methodName + " is ");
//            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
//            mv.visitVarInsn(LLOAD, durationId);
//            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;", false);
//            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
//            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
    }
}
