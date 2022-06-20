package com.dream.algorithmsdemo.java.priorityqueue;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/19
 */
public class MinPriorityQueueTest {

    public static void main(String[] args) {
        MinPriorityQueue<String> minPriorityQueue = new MinPriorityQueue<>(10);
        minPriorityQueue.insert("G");
        minPriorityQueue.insert("F");
        minPriorityQueue.insert("E");
        minPriorityQueue.insert("D");
        minPriorityQueue.insert("C");
        minPriorityQueue.insert("B");
        minPriorityQueue.insert("A");

        while (!minPriorityQueue.isEmpty()){
            System.out.println(minPriorityQueue.delMin());
        }

    }
}
