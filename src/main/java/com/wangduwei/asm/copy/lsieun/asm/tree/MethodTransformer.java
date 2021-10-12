package com.wangduwei.asm.copy.lsieun.asm.tree;

import org.objectweb.asm.tree.MethodNode;

public class MethodTransformer {
    protected MethodTransformer mt;

    public MethodTransformer(MethodTransformer mt) {
        this.mt = mt;
    }

    public void transform(MethodNode mn) {
        if (mt != null) {
            mt.transform(mn);
        }
    }
}
