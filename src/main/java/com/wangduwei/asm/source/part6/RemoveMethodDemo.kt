package com.wangduwei.asm.source.part6

import com.wangduwei.asm.source.utils.ClassOutputUtil
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.tree.ClassNode

class RemoveMethodDemo {
    fun test() {

    }
}

fun main() {
    val classReader = ClassReader("com.wangduwei.asm.source.part6.RemoveMethodDemo")

    val classNode = ClassNode()
    classReader.accept(classNode, ClassReader.SKIP_DEBUG)

    val removeMethod = RemoveMethodTransformer("test", "()V")
    removeMethod.transform(classNode)

    // 尝试输出进行查看
    val classWriter = ClassWriter(ClassWriter.COMPUTE_MAXS)
    classNode.accept(classWriter)
    //输出文件查看
    ClassOutputUtil.byte2File("asm_example/files/RemoveMethodDemo.class", classWriter.toByteArray())
}



