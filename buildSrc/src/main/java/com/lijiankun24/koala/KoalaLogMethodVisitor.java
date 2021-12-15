package com.lijiankun24.koala;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * KoalaLogMethodVisitor.java
 * <p>
 * Created by lijiankun24 on 2018/7/29.
 */
class KoalaLogMethodVisitor extends AdviceAdapter {

    private static final String COST_ANNOTATION_DESC = "Lcom/lijiankun24/koala/KoalaLog;";

    private boolean isInjected = false;

    private int startTimeId;

    private int methodId;

    private String className;

    private String methodName;

    private String desc;

    private boolean isStaticMethod;

    private Type[] argumentArrays;

    KoalaLogMethodVisitor(int api, MethodVisitor mv, int access, String className, String methodName, String desc) {
        super(api, mv, access, methodName, desc);
        this.className = className;
        this.methodName = methodName;
        this.desc = desc;
        argumentArrays = Type.getArgumentTypes(desc);
        isStaticMethod = ((access & Opcodes.ACC_STATIC) != 0);
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
            int index = isStaticMethod?0:1;
            for (int i = 0; i < argumentArrays.length; i++) {
                Type type = argumentArrays[i];
                //获取对应类型的加载指令
                int opcode = toVarInstruction(type);
                //入栈局部变量：加载指定索引位置的局部变量
                mv.visitVarInsn(opcode, index);
                box(type);
                //更新索引，double和long占用两个slot
                index += opcode == DLOAD || opcode == LLOAD ? 2 : 1;
                mv.visitVarInsn(ILOAD, methodId);
                visitMethodInsn(INVOKESTATIC, "com/lijiankun24/koala/core/MethodCache", "addMethodArgument",
                        "(Ljava/lang/Object;I)V", false);
            }

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
            mv.visitMethodInsn(INVOKESTATIC, "com/lijiankun24/koala/core/MethodCache", "updateMethodInfo",
                    "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JI)V", false);

            mv.visitVarInsn(ILOAD, methodId);
            mv.visitMethodInsn(INVOKESTATIC, "com/lijiankun24/koala/core/MethodCache",
                    "printMethodInfo", "(I)V", false);
        }
    }
    public static int toVarInstruction(Type type) {

        if (type == BOOLEAN_TYPE || type == BYTE_TYPE || type == CHAR_TYPE || type == INT_TYPE || type == SHORT_TYPE) {
            return ILOAD;
        } else if (type == DOUBLE_TYPE) {
            return DLOAD;
        } else if (type == FLOAT_TYPE) {
            return FLOAD;
        } else if (type == LONG_TYPE) {
            return LLOAD;
        } else {
            //对象类型
            return ALOAD;
        }

    }
}
