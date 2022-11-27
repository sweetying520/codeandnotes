package com.dream.javabase.designpatterns.enjoy;

import java.util.HashMap;
import java.util.Map;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/27
 */
//1、创建一个学生类
class Student {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

//2、享元工厂类
class StudentFactory {
    private static final Map<Object,Object> stuMap = new HashMap<>();

    public static Student getStudent(String key){
        if(stuMap.containsKey(key)){
            return (Student) stuMap.get(key);
        }else{
            Student stu = new Student();
            stuMap.put(key, stu);
            return stu;
        }
    }
}

//3、测试
public class EnjoyClient {

    private static final String[] keys = new String[]{"二代","小明","小红","张三","李四","二代","小红"};

    public static void main(String[] args) {
        for (String key : keys) {
            final Student student = StudentFactory.getStudent(key);
            student.setName(key);
            System.out.println(student.getName() + " HashCode："+ student.hashCode());
        }
    }
}
