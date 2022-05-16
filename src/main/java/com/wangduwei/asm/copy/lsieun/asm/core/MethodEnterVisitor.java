package com.wangduwei.asm.copy.lsieun.asm.core;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 演示：进入方法时添加日志
 * from：https://lsieun.github.io/java-asm-01/method-modify-enter-and-exit.html
 *
 */
public class MethodEnterVisitor extends ClassVisitor {
    public MethodEnterVisitor(int api,
                              ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    /**
     *
     * 构造函数中super必须在第一行
     * Java requires that if you call this() or super() in a constructor, it must be the first statement.
     */
    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (mv != null && !"<init>".equals(name)) {
            mv = new MethodEnterAdapter(api, mv);
        }
        return mv;
    }

    private static class MethodEnterAdapter extends MethodVisitor {
        public MethodEnterAdapter(int api, MethodVisitor methodVisitor) {
            super(api, methodVisitor);
        }

        @Override
        public void visitCode() {
            // 添加System.out.println(Method Enter...)
            super.visitFieldInsn(Opcodes.GETSTATIC,
                    "java/lang/System",
                    "out",
                    "Ljava/io/PrintStream;");

            super.visitLdcInsn("Method Enter...");

            super.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                    "java/io/PrintStream",
                    "println",
                    "(Ljava/lang/String;)V",
                    false);

            // 其次，调用父类的方法实现
            super.visitCode();
        }
    }
}
