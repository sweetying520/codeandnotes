package com.dream.algorithmsdemo.java.heap;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/17
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class HeapSort<T extends Comparable<T>> {

    private static boolean less(Comparable[] heap,int i,int j){
        return heap[i].compareTo(heap[j]) < 0;
    }

    private static void exch(Comparable[] heap,int i,int j){
        Comparable temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /**
     * 根据待排序的数组构建堆的数组
     */
    private static void createHeap(Comparable[] source,Comparable[] heap){

    }

    public static void sort(Comparable[] source){

    }

    private static void sink(Comparable[] head,int target,int range){

    }
}
