package com.lijiankun24.koala;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * CostTimeVisitor.java
 * <p>
 * Created by lijiankun03 on 2018/7/29.
 */
public class CostTimeVisitor extends ClassVisitor {

    public CostTimeVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
        methodVisitor = new CostTimeMethodVisitor(Opcodes.ASM5, methodVisitor, access, name, desc);
        return methodVisitor;
    }
}
