package com.wangduwei.asm.copy.lsieun.asm.core;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 在方法退出前添加日志
 */
public class MethodExitVisitor extends ClassVisitor {

    public MethodExitVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access,
                                     String name,
                                     String descriptor,
                                     String signature,
                                     String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (mv != null && !"<init>".equals(name)) {
            mv = new MethodExitAdapter(api, mv);
        }
        return mv;
    }

    private static class MethodExitAdapter extends MethodVisitor {
        public MethodExitAdapter(int api, MethodVisitor methodVisitor) {
            super(api, methodVisitor);
        }

        @Override
        public void visitInsn(int opcode) {
            // 判断是退出逻辑：正常退出或异常退出
            if (opcode == Opcodes.ATHROW || (opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)) {
                super.visitFieldInsn(Opcodes.GETSTATIC,
                        "java/lang/System",
                        "out",
                        "Ljava/io/PrintStream;");

                super.visitLdcInsn("Method Exit...");

                super.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                        "java/io/PrintStream",
                        "println",
                        "(Ljava/lang/String;)V",
                        false);
            }

            // 其次，调用父类的方法实现
            super.visitInsn(opcode);
        }
    }
}
