package com.wangduwei.javassist;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 * @desc:
 * @auther:duwei
 * @date:2019/2/11
 */
public class JavassistDemo {

    public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.get("com.wangduwei.javassist.ClassToModify");

        CtMethod method = ctClass.getDeclaredMethod("sayHellow");

        method.insertBefore("System.out.println(\"before say\");");//成功在原来的方法前面注入代码
        ctClass.writeFile("java\\build\\classes\\java\\main");//这里要指定路径

        ClassToModify classToModify = new ClassToModify();
        classToModify.sayHellow();
    }


}
