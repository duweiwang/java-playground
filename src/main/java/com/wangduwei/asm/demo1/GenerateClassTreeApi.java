package com.wangduwei.asm.demo1;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

/**
 * @author 杜伟
 * @date 2021/8/7 3:22 PM
 *
 * TreeApi的使用
 *
 */
public class GenerateClassTreeApi {


    public static void generate() {

        ClassNode classNode = new ClassNode();

        classNode.version = Opcodes.V1_8;
        classNode.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE;

        classNode.name = "com/wangduwei/asm/demo1/TestTreeApi";
        classNode.superName = "java/lang/Object";
//        classNode.interfaces.add("");//todo

        //public static final String LESS = "sss
        classNode.fields.add(new FieldNode(
                Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC,
                "LESS", "Ljava/lang/String;", null, "sss"
        ));

        //public abstract Object compareTo()
        classNode.methods.add(new MethodNode(
                Opcodes.ACC_ABSTRACT + Opcodes.ACC_PUBLIC,
                "compareTo", "(Ljava/lang/Objectl)I", null, null
        ));

        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        classNode.accept(classWriter);


    }


    /**
     * TODO 待验证
     * public void checkAndSet(int var1) {
     *         if(var1 >= 0) {
     *             this.f = var1;
     *         } else {
     *             throw new IllegalArgumentException();
     *         }
     *     }
     */
    public static MethodNode createMethod(){
        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC,"checkAndSet","(I)V",null,null);
        InsnList insnList = methodNode.instructions;

        insnList.add(new VarInsnNode(Opcodes.ILOAD, 1));

        LabelNode label = new LabelNode();
        insnList.add(new JumpInsnNode(Opcodes.IFLT, label));
        insnList.add(new VarInsnNode(Opcodes.ALOAD, 0));
        insnList.add(new VarInsnNode(Opcodes.ILOAD, 1));
        insnList.add(new FieldInsnNode(Opcodes.PUTFIELD, "org/by/Cwtest", "f", "I"));
        LabelNode end = new LabelNode();
        insnList.add(new JumpInsnNode(Opcodes.GOTO, end));
        insnList.add(label);
        insnList.add(new FrameNode(Opcodes.F_SAME, 0, null, 0, null));
        insnList.add(new TypeInsnNode(Opcodes.NEW, "java/lang/IllegalArgumentException"));
        insnList.add(new InsnNode(Opcodes.DUP));
        insnList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "java/lang/IllegalArgumentException", "<init>", "()V",false));
        insnList.add(new InsnNode(Opcodes.ATHROW));
        insnList.add(end);
        insnList.add(new FrameNode(Opcodes.F_SAME, 0, null, 0, null));
        insnList.add(new InsnNode(Opcodes.RETURN));
        methodNode.maxStack = 2;
        methodNode.maxLocals = 2;
        return methodNode;
    }


}
