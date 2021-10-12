package com.wangduwei.asm.copy.lsieun.asm.template;

import com.wangduwei.asm.copy.lsieun.utils.FileUtils;
import com.wangduwei.asm.copy.lsieun.utils.ReadUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class MethodFinallyAdapterRun {
    public static void main(String[] args) {
        String relative_path = "sample/HelloWorld.class";
        String filepath = FileUtils.getFilePath(relative_path);
        byte[] bytes1 = ReadUtils.readByPath(filepath);

        //（1）构建ClassReader
        ClassReader cr = new ClassReader(bytes1);

        //（2）构建ClassWriter
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        //（3）串连ClassVisitor
        ClassVisitor cv = new MethodFinallyAdapter(Opcodes.ASM9, cw);

        //（4）两者进行结合
        cr.accept(cv, ClassReader.SKIP_FRAMES);

        //（5）重新生成Class
        byte[] bytes2 = cw.toByteArray();

        FileUtils.writeBytes(filepath, bytes2);
    }
}
