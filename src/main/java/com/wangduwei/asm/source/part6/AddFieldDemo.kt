package com.wangduwei.asm.source.part6

import com.wangduwei.asm.source.utils.ADLog
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.ClassNode

/*
在使用树 API 进行类转换时，所花费的时间和内存都比使用核心 API 的多，但是使用树 API 进行转换时跟简单一些.
比如对于整个类添加数字签名,使用核心 API 需要将整个类访问完之后才能添加，这个时候就晚了，但是对于树 API 则没有这种限制.
事实上,对于这种转换,使用核心 API 需要先用 ClassReader 将类扫描一遍，然后在结合 ClassWriter 等转换链完成转换。

结论是：树 API 通常用于那些不能由核心 API 一次实现的转换。
 */

class AddFieldDemo {
}

fun main() {
    val classReader = ClassReader("com.wangduwei.asm.source.part6.AddFieldDemo")
    val classNode = ClassNode()

    val addFieldTransform = AddFieldTransform(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
        "addField",
        "Ljava/lang/String;")
    classReader.accept(classNode, ClassReader.SKIP_DEBUG)

    addFieldTransform.transform(classNode)

    // 尝试输出进行查看
    val classWriter = ClassWriter(ClassWriter.COMPUTE_MAXS)
    classNode.accept(classWriter)
    //输出文件查看
//    ClassOutputUtil.byte2File("asm_example/files/AddFieldDemo.class", classWriter.toByteArray())
//    ClassOutputUtil.byte2File(AddFieldDemo::class.java.getResource("/").path+"/AddFieldDemo.class", classWriter.toByteArray())

    ADLog.info(String(classWriter.toByteArray(), Charsets.US_ASCII))
}

