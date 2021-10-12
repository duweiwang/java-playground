package com.wangduwei.java_basic.string;

/**
 * <p>
 *
 * @auther : wangduwei
 * @since : 2019/9/5  16:52
 **/
public class MethodUsage {

    public static void main(String[] args) {
        MethodUsage.isWhite();

    }


    public static void isWhite() {

        boolean a = Character.isWhitespace(Character.SPACE_SEPARATOR);
        boolean b = Character.isWhitespace(Character.LINE_SEPARATOR);
        boolean c = Character.isWhitespace(Character.PARAGRAPH_SEPARATOR);

        boolean d = Character.isWhitespace('\n');//换行
        boolean e = Character.isWhitespace('\r');//回车
        boolean f = Character.isWhitespace('\t');//tab
        boolean g = Character.isWhitespace(' ');
        boolean h = Character.isWhitespace('\f');//换页

        out(" a = " + a);
        out(" b = " + b);
        out(" c = " + c);
        out(" d = " + d);
        out(" e = " + e);
        out(" f = " + f);
        out(" g = " + g);
        out(" h = " + h);
    }

    public static void out(String string) {
        System.out.println(string);
    }
}
