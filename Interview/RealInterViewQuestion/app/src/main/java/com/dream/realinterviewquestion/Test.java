package com.dream.realinterviewquestion;

import java.util.PriorityQueue;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/3/12
 */
public class Test {

    public static void main(String[] args) {

//        MedianFinder medianFinder = new MedianFinder();
//        medianFinder.addNum(2);
//        medianFinder.addNum(3);
//        medianFinder.addNum(4);
//        medianFinder.addNum(5);
//        medianFinder.findMedian();
//        PriorityQueue<Integer> queueMax = new PriorityQueue<>((a,b) -> b - a);
//        queueMax.offer(5);
//        queueMax.offer(2);
//        queueMax.offer(4);
//        queueMax.offer(3);
//        queueMax.offer(13);
//        System.out.println(queueMax.poll());
//
//        PriorityQueue<Integer> queMin = new PriorityQueue<>();
//        queMin.offer(5);
//        queMin.offer(2);
//        queMin.offer(4);
//        queMin.offer(3);
//        queMin.offer(13);
//        System.out.println(queMin.poll());

    }
}

class MedianFinder {

    PriorityQueue<Integer> queMin = null;
    PriorityQueue<Integer> queMax = null;

    /** initialize your data structure here. */
    public MedianFinder() {
        queMin = new PriorityQueue<>((a,b)->(b-a));
        queMax = new PriorityQueue<>((a, b)->(a-b));
    }

    public void addNum(int num) {
        if(queMin.isEmpty() || num <= queMin.peek()){
            queMin.offer(num);
            if(queMax.size() + 1 < queMin.size()){
                queMax.offer(queMin.poll());
            }
        }else{
            queMax.offer(num);
            if(queMax.size() > queMin.size()){
                queMin.offer(queMax.poll());
            }
        }
    }

    public double findMedian() {
        if(queMin.size() > queMax.size()){
            return queMin.peek();
        }

        return (queMin.peek() + queMax.peek()) / 2.0;
    }
}