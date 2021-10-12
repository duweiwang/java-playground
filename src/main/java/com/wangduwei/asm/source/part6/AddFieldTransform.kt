package com.wangduwei.asm.source.part6

import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.FieldNode

/**
* @author 杜伟
* @date 2021/8/11 8:50 PM
* 
*/
class AddFieldTransform(var filedAccess: Int,
                        var fieldName: String,
                        var fieldDesc: String) : ClassTransformer() {

    override fun transform(cn: ClassNode) {
        var isPresent = false
        if (cn?.fields != null) {
            for (fieldNode in cn.fields) {
                if (fieldNode.name == fieldName) {
                    isPresent = true
                    break;
                }
            }
            if (!isPresent) {
                cn.fields.add(FieldNode(filedAccess, fieldName, fieldDesc, null, null))
            }
        }
        super.transform(cn)
    }
}