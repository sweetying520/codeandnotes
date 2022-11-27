package com.dream.javabase.designpatterns.prototype;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/17
 */
class Test {

    static class Student implements Cloneable{
        public String name;

        public Student(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    '}';
        }

        @NonNull
        @Override
        protected Student clone()  {
            try {
                return (Student) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    public static void main(String[] args) {
        //1、浅拷贝
//        ArrayList<Student> stringList = new ArrayList<>();
//        Student stu = new Student("erdai");
//        stringList.add(stu);
//        stringList.add(new Student("erdai666"));
//        System.out.println(stringList);
//        //浅拷贝核心代码
//        ArrayList<Student> newStringList = (ArrayList<Student>) stringList.clone();
//        //ArrayList<Student> newStringList = new ArrayList<>(stringList);
//        System.out.println(newStringList);
//        //改变 stu 内的值，结果两个集合的值都变了
//        stu.name = "erdai777";
//        System.out.println(stringList);
//        System.out.println(newStringList);

        //2、深拷贝
        ArrayList<Student> stringList = new ArrayList<>();
        Student stu = new Student("erdai");
        stringList.add(stu);
        stringList.add(new Student("erdai666"));
        System.out.println(stringList);
        ArrayList<Student> newStringList = new ArrayList<>();
        for (Student student : stringList) {
            newStringList.add(student.clone());
        }
        System.out.println(newStringList);
        //改变 stu 内的值，只有 StringList 的值会变，newStringList 不会变
        stu.name = "erdai777";
        System.out.println(stringList);
        System.out.println(newStringList);

        //3、使用 Collections.copy 实现浅拷贝
//        ArrayList<Student> stringList = new ArrayList<>();
//        Student stu = new Student("erdai");
//        stringList.add(stu);
//        stringList.add(new Student("erdai666"));
//        System.out.println(stringList);
//        ArrayList<Student> newStringList = new ArrayList<>();
//        Collections.addAll(newStringList,new Student[stringList.size()]);
//        Collections.copy(newStringList,stringList);
//        System.out.println(newStringList);
//        //改变 stu 内的值，结果两个集合的值都变了
//        stu.name = "erdai777";
//        System.out.println(stringList);
//        System.out.println(newStringList);
    }
}
