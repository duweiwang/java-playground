package com.wangduwei.java_basic.string;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : wangduwei
 * @date : 2020/6/25
 * @description :
 */
public class Test {

    public static void main(String[] args) {
        String name = "**爱的情缘";
        String string = "fdsgdg@" + name + "u433987";
        Pattern pattern = Pattern.compile("(@" + replaceSpecialWord(name) + ")", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            System.out.print("find");
        }else{
            System.out.print("non");
        }
    }

    public static String replaceSpecialWord(String keyword) {
        String[] fbsArr = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};
        for (String key : fbsArr) {
            if (keyword.contains(key)) {
                keyword = keyword.replace(key, "\\" + key);
            }
        }
        return keyword;
    }

    public static String replaceAll(String str) {
        return str.replace("\\", "\\\\").replace("*", "\\*")
                .replace("+", "\\+").replace("|", "\\|")
                .replace("{", "\\{").replace("}", "\\}")
                .replace("(", "\\(").replace(")", "\\)")
                .replace("^", "\\^").replace("$", "\\$")
                .replace("[", "\\[").replace("]", "\\]")
                .replace("?", "\\?").replace(",", "\\,")
                .replace(".", "\\.").replace("&", "\\&");
    }

}
