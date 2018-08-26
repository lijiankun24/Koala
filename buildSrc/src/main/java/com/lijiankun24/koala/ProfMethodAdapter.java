package com.lijiankun24.koala;

import com.lijiankun24.koala.core.MethodCache;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ProfMethodAdapter extends MethodVisitor {

    private int mMethodId;      // 方法 Id

    public ProfMethodAdapter(MethodVisitor visitor, String fileName, String className, String methodName) {
        super(Opcodes.ASM5, visitor);
        mMethodId = MethodCache.Request();
        MethodCache.UpdateMethodName(mMethodId, fileName, className, methodName);
    }

    @Override
    public void visitLineNumber(int line, Label start) {
        MethodCache.UpdateLineNum(mMethodId, line);
        super.visitLineNumber(line, start);
    }

    @Override
    public void visitInsn(int inst) {
        switch (inst) {
            case Opcodes.ARETURN:
            case Opcodes.DRETURN:
            case Opcodes.FRETURN:
            case Opcodes.IRETURN:
            case Opcodes.LRETURN:
            case Opcodes.RETURN:
            case Opcodes.ATHROW:
//                this.visitLdcInsn(mMethodId);
//                this.visitMethodInsn(INVOKESTATIC, "com/taobao/profile/Profiler", "End", "(I)V");
                break;
            default:
                break;
        }
        super.visitInsn(inst);
    }

}
