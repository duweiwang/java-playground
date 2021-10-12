package com.wangduwei.asm.source.part6

import com.wangduwei.asm.source.utils.ADLog
import org.objectweb.asm.tree.ClassNode

/**
 * @author 杜伟
 * @date 2021/8/11 8:49 PM
 *
 */
class RemoveMethodTransformer(
    var methodName: String,
    var methodDesc: String
) : ClassTransformer() {

    override fun transform(cn: ClassNode) {
        val methodNodes = cn.methods
        methodNodes?.forEach { methodNode ->
            ADLog.info("node = " + methodNode.name + ", desc = " + methodNode.desc)
            if (methodNode.name == methodName && methodNode.desc == methodDesc) {
                methodNodes.remove(methodNode)
            }
        }

    }
}