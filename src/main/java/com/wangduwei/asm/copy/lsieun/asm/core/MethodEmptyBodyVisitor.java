package com.wangduwei.asm.copy.lsieun.asm.core;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import static org.objectweb.asm.Opcodes.*;

/**
 * 清空方法的实现
 *
 * 实现方式：删除旧的，添加新的空方法
 *
 */
public class MethodEmptyBodyVisitor extends ClassVisitor {
    private String owner;
    private final String methodName;
    private final String methodDesc;

    public MethodEmptyBodyVisitor(int api,
                                  ClassVisitor classVisitor,
                                  String methodName,
                                  String methodDesc) {
        super(api, classVisitor);
        this.methodName = methodName;
        this.methodDesc = methodDesc;
    }

    @Override
    public void visit(int version,
                      int access,
                      String name,
                      String signature,
                      String superName,
                      String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.owner = name;
    }

    @Override
    public MethodVisitor visitMethod(int access,
                                     String name,
                                     String descriptor,
                                     String signature,
                                     String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (mv != null && methodName.equals(name) && methodDesc.equals(descriptor)) {
            boolean isAbstractMethod = (access & ACC_ABSTRACT) != 0;
            boolean isNativeMethod = (access & ACC_NATIVE) != 0;
            if (!isAbstractMethod && !isNativeMethod) {
                generateNewBody(mv, owner, access, name, descriptor);
                return null;
            }
        }
        return mv;
    }

    protected void generateNewBody(MethodVisitor mv,
                                   String owner,
                                   int methodAccess,
                                   String methodName,
                                   String methodDesc) {
        // (1) method argument types and return type
        Type t = Type.getType(methodDesc);
        Type[] argumentTypes = t.getArgumentTypes();
        Type returnType = t.getReturnType();


        // (2) compute the size of local variable and operand stack
        boolean isStaticMethod = ((methodAccess & Opcodes.ACC_STATIC) != 0);
        int localSize = isStaticMethod ? 0 : 1;
        for (Type argType : argumentTypes) {
            localSize += argType.getSize();
        }
        int stackSize = returnType.getSize();


        // (3) method body
        mv.visitCode();
        if (returnType.getSort() == Type.VOID) {
            mv.visitInsn(RETURN);
        }
        else if (returnType.getSort() >= Type.BOOLEAN && returnType.getSort() <= Type.DOUBLE) {
            mv.visitInsn(returnType.getOpcode(ICONST_1));
            mv.visitInsn(returnType.getOpcode(IRETURN));
        }
        else {
            mv.visitInsn(ACONST_NULL);
            mv.visitInsn(ARETURN);
        }
        mv.visitMaxs(stackSize, localSize);
        mv.visitEnd();
    }
}
