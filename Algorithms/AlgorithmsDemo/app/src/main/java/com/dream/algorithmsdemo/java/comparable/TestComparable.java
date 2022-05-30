package com.dream.algorithmsdemo.java.comparable;

/**
 * function: 学生比较测试
 *
 * @author zy
 * @since 2022/5/29
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class TestComparable {

    public static void main(String[] args) {
        Student stu1 = new Student("erdai",18);
        Student stu2 = new Student("xiaomi",30);
        System.out.println(getMax(stu1,stu2));
    }

    public static Comparable getMax(Comparable c1,Comparable c2){
        int result = c1.compareTo(c2);
        System.out.println(result);
        if(result < 0){
            //则 c1 比 c2 小
            return c2;
        }else if(result > 0){
            //则 c1 比 c2 大
            return c1;
        }else {
            //相等
            return c1;
        }
    }
}


