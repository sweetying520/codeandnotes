package com.dream.algorithmsdemo.java.sequence;

import androidx.annotation.NonNull;

import java.util.Iterator;

/**
 * function: 顺序表
 *
 * @author zy
 * @since 2022/5/29
 */
public class SequenceList<T> implements Iterable<T>{

    private final T[] eles;

    private final int capacity;

    private int N;


    public SequenceList(int capacity){
        this.eles = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.N = 0;
    }

    public void clear(){
        this.N = 0;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int length(){
        return N;
    }

    public T get(int index){
        return eles[index];
    }

    public void insert(T t){
        eles[N++] = t;
    }

    public void insert(int i,T t){
        for(int index = N;index > i;index--){
            if(index == capacity){
               return;
            }
            eles[index] = eles[index - 1];
        }
        eles[i] = t;
        N++;
    }

    public T remove(int i){
        T current = eles[i];
        for (int index = i;index < N - 1;index++){
            eles[index] = eles[index+1];
        }
        N--;
        return current;
    }

    public int indexOf(T t){
        for (int i = 0; i < N; i++) {
            if(eles[i].equals(t)){
                return i;
            }
        }
        return -1;
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return new SIterator();
    }


    private class SIterator implements Iterator<T>{

        private int cusor;

        public SIterator() {
            this.cusor = 0;
        }

        @Override
        public boolean hasNext() {
            return cusor < N;
        }

        @Override
        public T next() {
            return eles[cusor++];
        }
    }
}
