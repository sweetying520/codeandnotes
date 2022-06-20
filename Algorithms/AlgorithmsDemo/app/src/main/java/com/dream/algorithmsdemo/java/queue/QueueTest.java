package com.dream.algorithmsdemo.java.queue;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/14
 */
public class QueueTest {

    public static void main(String[] args) {
        Queue<Integer> integerQueue = new Queue<>();
        integerQueue.enQueue(1);
        integerQueue.enQueue(2);
        integerQueue.enQueue(3);
        integerQueue.enQueue(4);

        for (Integer integer : integerQueue) {
            System.out.println(integer);
        }

        System.out.println();
        Integer integer = integerQueue.deQueue();
        System.out.println(integer);

        System.out.println();
        System.out.println(integerQueue.size());
    }
}
