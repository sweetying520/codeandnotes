package com.dream.algorithmsdemo.java.priorityqueue;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/19
 */
public class MaxPriorityQueueTest {

    public static void main(String[] args) {
        MaxPriorityQueue<String> maxPriorityQueue = new MaxPriorityQueue<>(10);
        maxPriorityQueue.insert("A");
        maxPriorityQueue.insert("B");
        maxPriorityQueue.insert("C");
        maxPriorityQueue.insert("D");
        maxPriorityQueue.insert("E");
        maxPriorityQueue.insert("F");
        maxPriorityQueue.insert("G");

        while (!maxPriorityQueue.isEmpty()){
            System.out.println(maxPriorityQueue.delMax());
        }

    }
}
