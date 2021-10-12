package com.wangduwei.asm.source.field

import com.wangduwei.asm.source.utils.ClassOutputUtil
import org.objectweb.asm.*
import org.objectweb.asm.util.CheckFieldAdapter

class CheckFieldInsn {
}


fun main() {
    val classReader = ClassReader("com.wangduwei.asm.source.field.CheckFieldInsn")
    val classWriter = ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
    classReader.accept(object: ClassVisitor(Opcodes.ASM7, classWriter){
        var isExist = false
        override fun visitField(
            access: Int,
            name: String?,
            descriptor: String?,
            signature: String?,
            value: Any?
        ): FieldVisitor? {
            if (name == "TAG") {
                isExist = true
            }

            return super.visitField(access, name, descriptor, signature, value)
        }

        override fun visitEnd() {
            super.visitEnd()
            if (!isExist) {
                val fieldVisitor = cv.visitField(Opcodes.ACC_PUBLIC, "TAG", "Ljava/lang/String;",null,"CheckField")
                val checkFieldAdapter = CheckFieldAdapter(fieldVisitor)
                checkFieldAdapter.visitAnnotation("Lcom/andoter/Interface;", true)
                checkFieldAdapter.visitEnd()
            }
        }
    }, ClassReader.SKIP_DEBUG)

    ClassOutputUtil.byte2File(CheckFieldInsn::class.java.getResource("/").path + "/CheckFieldInsn.class", classWriter.toByteArray())
}