package com.wangduwei.asm.source.part4

import com.wangduwei.asm.source.utils.ClassOutputUtil
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes

class DeprecatedDump {
}

fun main() {
    val deprecatedDump = DeprecatedDumpAdapter.dump()
    //输出文件查看
    ClassOutputUtil.byte2File("asm_example/files/DeprecatedDumpAdapter.class", deprecatedDump)
    /*
    查看输出的文件：
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Deprecated {
    }
     */
}

/**
 * 通过代码示例创建一个注解类，一个无值，一个具有枚举值。同样对于注解也有：TraceAnnotationVisitor 和 CheckAnnotationAdapter 用于
 * 输出和检测是否合法正确
 */
object DeprecatedDumpAdapter : Opcodes {
    fun dump(): ByteArray {
        val classWriter = ClassWriter(0)
        classWriter.visit(Opcodes.ASM7, Opcodes.ACC_PUBLIC + Opcodes.ACC_ANNOTATION + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE,
            "java/lang/Deprecated", null, "java/lang/Object", arrayOf( "java/lang/annotation/Annotation" )
            )
        var av = classWriter.visitAnnotation("Ljava/lang/annotation/Documented;", true)
        av.visitEnd()

        av = classWriter.visitAnnotation("Ljava/lang/annotation/Retention;", true)
        av.visitEnum("value", "Ljava/lang/annotation/RetentionPolicy;",
            "RUNTIME")
        av.visitEnd()

        classWriter.visitEnd()
        return classWriter.toByteArray()
    }
}