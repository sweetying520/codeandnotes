package com.dream.realinterviewquestion.memory;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/3/9
 */
public class MemoryProfile3 {

    public static void main(String[] args) {
        //补充1
        Integer j = 12;
        Integer j1 = 12;
        System.out.println(j == j1);
        Integer j2 = 333;
        Integer j3 = 333;
        System.out.println(j2 == j3);

        //补充2
        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);
        System.out.println("i1=i2   " + (i1 == i2));
        System.out.println("i1=i2+i3   " + (i1 == i2 + i3));
        System.out.println("i1=i4   " + (i1 == i4));
        System.out.println("i4=i5   " + (i4 == i5));
        System.out.println("i4=i5+i6   " + (i4 == i5 + i6));
        System.out.println("40=i5+i6   " + (40 == i5 + i6));
    }
}
