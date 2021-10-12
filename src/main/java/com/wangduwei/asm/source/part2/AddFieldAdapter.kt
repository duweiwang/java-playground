package com.wangduwei.asm.source.part2

import com.wangduwei.asm.source.utils.ADLog
import com.wangduwei.asm.source.utils.AccessCodeUtils
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.Opcodes

class AddFieldAdapter(private var version: Int, classVisitor: ClassVisitor) : ClassVisitor(version, classVisitor) {
    var isExists = false
    lateinit var filedName: String
    private var filedAccessFlag: Int = Opcodes.ACC_PUBLIC
    lateinit var fieldDescription: String
    var classVisitor: ClassVisitor = classVisitor


    constructor(
        version: Int,
        classVisitor: ClassVisitor,
        filedName: String,
        filedAccess: Int,
        fieldDescription: String
    ) : this(version, classVisitor) {
        this.filedAccessFlag = filedAccess
        this.fieldDescription = fieldDescription
        this.filedName = filedName
    }

    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?
    ) {
        ADLog.info("visit, version = $version, access = ${AccessCodeUtils.accCode2String(access)}, name = ${name}, signature = $signature")
        super.visit(version, access, name, signature, superName, interfaces)
    }

    override fun visitField(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        value: Any?
    ): FieldVisitor? {
        ADLog.info(
            "visitField: access=${AccessCodeUtils.accCode2String(access)},name=$name,descriptor=$descriptor," +
                    "signature=$signature,value=$value"
        )
        if (name == "name") {
            println("存在字段 name,value = ")
        }

        if (this.filedName == name) {
            this.isExists = true
        }
        return super.visitField(access, name, descriptor, signature, value)
    }

    /**
     * 不存在的话添加字段
     */
    override fun visitEnd() {
        super.visitEnd()
        if (!isExists) {
            val filedVisitor = cv.visitField(filedAccessFlag, filedName, fieldDescription, null, null)
            filedVisitor?.visitEnd()
        }
        ADLog.info("visitEnd")
    }
}