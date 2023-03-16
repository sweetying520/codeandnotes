package com.dream.realinterviewquestion.memory;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/3/9
 */
public class MemoryProfile2 {

    public static void main(String[] args) {
        //String场景1
        String str = "aaa";
        String str1 = new String("aaa");
        System.out.println(str == str1);
        System.out.println(str.equals(str1));


        //String场景2
        String s1 = "str";
        String s2 = "ing";
        String s3 = "str" + "ing";
        String s4 = s1 + s2;
        String s5 = "string";
        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
        System.out.println(s4 == s5);

        //String场景3
        String str2 = "ccc" + "ddd" + "eee" + "fff";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ccc").append("ddd").append("eee").append("fff");
        System.out.println(str2.equals(stringBuilder.toString()));
    }
}