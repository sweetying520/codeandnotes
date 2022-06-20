package com.dream.algorithmsdemo.java.stack;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/14
 */
public class StackTest {

    public static void main(String[] args) {
        Stack<Integer> integerStack = new Stack<>();
        integerStack.push(1);
        integerStack.push(2);
        integerStack.push(3);
        integerStack.push(4);
        for (Integer integer : integerStack) {
            System.out.println(integer);
        }

        System.out.println();
        System.out.println(integerStack.pop());
        System.out.println(integerStack.pop());

        System.out.println();
        integerStack.clear();
        System.out.println(integerStack.size());
    }
}
