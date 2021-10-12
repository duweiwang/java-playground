package com.wangduwei.asm.source.field

import com.wangduwei.asm.source.utils.ADLog
import org.objectweb.asm.*
import javax.annotation.Nullable

class FieldVisitorDemo {
    private val TAG = "FieldVisitor"

    @Nullable
    private var obj:Any? = null
}

fun main() {
    val classReader = ClassReader("com.wangduwei.asm.source.field.FieldVisitorDemo")
    classReader.accept(object : ClassVisitor(Opcodes.ASM7) {
        override fun visitField(
            access: Int,
            name: String?,
            descriptor: String?,
            signature: String?,
            value: Any?
        ): FieldVisitor? {
            return FiledVisitorPrinter(super.visitField(access, name, descriptor, signature, value))
        }

        override fun visitEnd() {
            super.visitEnd()

        }
    }, ClassReader.SKIP_DEBUG)
}

class FiledVisitorPrinter(fieldVisitor: FieldVisitor?) : FieldVisitor(Opcodes.ASM7, fieldVisitor) {

    override fun visitEnd() {
        super.visitEnd()
        ADLog.info("visitEnd")
    }

    override fun visitAnnotation(descriptor: String?, visible: Boolean): AnnotationVisitor {
        ADLog.info("visitAnnotation, des = $descriptor, visiable = $visible")
        return super.visitAnnotation(descriptor, visible)
    }

    override fun visitTypeAnnotation(
        typeRef: Int,
        typePath: TypePath?,
        descriptor: String?,
        visible: Boolean
    ): AnnotationVisitor {
        return super.visitTypeAnnotation(typeRef, typePath, descriptor, visible)
    }

    override fun visitAttribute(attribute: Attribute?) {
        super.visitAttribute(attribute)
        ADLog.info("visitAttribute")
    }
}

