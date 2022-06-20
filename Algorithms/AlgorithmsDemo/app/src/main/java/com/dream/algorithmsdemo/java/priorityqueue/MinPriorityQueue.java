package com.dream.algorithmsdemo.java.priorityqueue;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/19
 */
public class MinPriorityQueue<T extends Comparable<T>> {

    private T[] items;
    private int N;

    public MinPriorityQueue(int capacity) {
        items = (T[]) new Comparable[capacity + 1];
        N = 0;

    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    private boolean less(int i,int j){
        return items[i].compareTo(items[j]) < 0;
    }


    private void exch(int i,int j){
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }


    public void insert(T t){
        items[++N] = t;
        swim(N);
    }

    public T delMin(){
        T min = items[1];
        exch(1,N);
        N--;
        sink(1);
        return min;
    }

    private void swim(int k){
        while (k > 1){
            if(less(k,k / 2)){
                exch(k,k / 2);
            }
            k = k / 2;
        }
    }

    private void sink(int k){
        while (2 * k <= N){
            int min;
            if(2 * k + 1 <= N){
                if(less(2 * k,2 * k +1)){
                    min = 2 * k;
                }else {
                    min = 2 * k + 1;
                }
            }else {
                min = 2 * k;
            }

            if(less(k,min)){
                break;
            }

            exch(k,min);
            k = min;
        }
    }
}
