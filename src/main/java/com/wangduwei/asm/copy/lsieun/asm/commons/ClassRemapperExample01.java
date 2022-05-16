package com.wangduwei.asm.copy.lsieun.asm.commons;

import com.wangduwei.asm.copy.lsieun.utils.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.commons.ClassRemapper;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.commons.SimpleRemapper;

/**
 * 修改类名
 *
 *
 */
public class ClassRemapperExample01 {
    public static void main(String[] args) {
        String origin_name = "sample/HelloWorld";
        String target_name = "sample/GoodChild";
        String origin_filepath = getFilePath(origin_name);
        byte[] bytes1 = FileUtils.readBytes(origin_filepath);

        //（1）构建ClassReader
        ClassReader cr = new ClassReader(bytes1);

        //（2）构建ClassWriter
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        //（3）串连ClassVisitor
        Remapper remapper = new SimpleRemapper(origin_name, target_name);
        ClassVisitor cv = new ClassRemapper(cw, remapper);

        //（4）两者进行结合
        int parsingOptions = ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES;
        cr.accept(cv, parsingOptions);

        //（5）重新生成Class
        byte[] bytes2 = cw.toByteArray();

        String target_filepath = getFilePath(target_name);
        FileUtils.writeBytes(target_filepath, bytes2);
    }

    public static String getFilePath(String internalName) {
        String relative_path = String.format("%s.class", internalName);
        return FileUtils.getFilePath(relative_path);
    }
}
