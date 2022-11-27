package com.dream.javabase;


import com.dream.javabase.designpatterns.builder.Computer;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/8/5
 */
public class Test {

    public static void main(String[] args) {
        Object a = 1;
        Object b = true;
        Object str = "erdai666";

        final Computer computer = new Computer.Builder()
                .cpu("英特尔")
                .ram("8G")
                .rom("128G")
                .build();

        System.out.println(computer.getCpu());
        System.out.println(computer.getRam());
        System.out.println(computer.getRom());
    }
}
