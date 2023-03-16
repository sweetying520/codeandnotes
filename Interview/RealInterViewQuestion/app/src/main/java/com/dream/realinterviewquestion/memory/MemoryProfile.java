package com.dream.realinterviewquestion.memory;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/3/9
 */
public class MemoryProfile {

    public static void main(String[] args) {
        MemoryProfile mMemoryProfile = new MemoryProfile();
        Person p1 = new Person(25);
        Person p2 = new Person(30);
        mMemoryProfile.change(p1,p2);
        System.out.println(p1.getAge());
        System.out.println(p2.getAge());
    }


    private void change(Person mPerson1,Person mPerson2){
        Person mPerson3 = mPerson1;
        mPerson1 = mPerson2;
        mPerson2 = mPerson3;
    }
}

class Person {

    private int age;

    public Person(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}