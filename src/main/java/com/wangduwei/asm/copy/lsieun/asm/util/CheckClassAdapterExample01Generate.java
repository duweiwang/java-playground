package com.wangduwei.asm.copy.lsieun.asm.util;

import com.wangduwei.asm.copy.lsieun.utils.FileUtils;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.CheckClassAdapter;

public class CheckClassAdapterExample01Generate {
    public static void main(String[] args) throws Exception {
        String relative_path = "sample/HelloWorld.class";
        String filepath = FileUtils.getFilePath(relative_path);

        // (1) 生成byte[]内容
        byte[] bytes = dump();

        // (2) 保存byte[]到文件
        FileUtils.writeBytes(filepath, bytes);
    }

    public static byte[] dump() throws Exception {
        // (1) 创建ClassWriter对象
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassVisitor cv = new CheckClassAdapter(cw);

        // (2) 调用visitXxx()方法
        CodeUtils.generate(cv);

        // (3) 调用toByteArray()方法
        return cw.toByteArray();
    }
}
