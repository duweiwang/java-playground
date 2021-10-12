package com.wangduwei.asm.source.part6

import org.objectweb.asm.tree.ClassNode

/**
 * @author 杜伟
 * @date 2021/8/11 8:48 PM
 *
 */
open class ClassTransformer(protected val ct:ClassTransformer? = null) {

    open fun transform(cn: ClassNode) {
        ct?.transform(cn)
    }
}