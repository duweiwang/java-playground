package com.wangduwei.jol_samples;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

/**
 * @author 杜伟
 * @date 2021/10/5 3:47 PM
 */
public class PrintBoolean {


    public static void main(String[] args) throws Exception {
        out.println(VM.current().details());
        out.println(ClassLayout.parseClass(Boolean.class).toPrintable());
    }
}
