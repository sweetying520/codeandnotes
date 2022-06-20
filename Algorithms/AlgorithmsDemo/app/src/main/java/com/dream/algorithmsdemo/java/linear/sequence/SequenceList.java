package com.dream.algorithmsdemo.java.linear.sequence;

import android.app.LauncherActivity;

import androidx.annotation.NonNull;

import java.util.Iterator;

/**
 * function: 顺序表
 *
 * @author zy
 * @since 2022/5/29
 */
public class SequenceList<T> implements Iterable<T>{

    private T[] eles;


    private int N;


    public SequenceList(int capacity){
        this.eles = (T[]) new Object[capacity];
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
        if(N == eles.length){
            resize(2 * eles.length);
        }
        eles[N++] = t;
    }

    public void insert(int i,T t){
        if(N == eles.length){
            resize(2 * eles.length);
        }
        for(int index = N;index > i;index--){
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
        if(N < eles.length / 4){
            resize(eles.length / 2);
        }
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

    /**
     * 根据参数 newSize 重置 eles 的大小
     * @param newSize
     */
    public void resize(int newSize){
        //定义一个临时数组，指向原数组
        T[] temp = eles;
        //创建一个新数组
        eles = (T[]) new Object[newSize];
        //把愿数组的数据拷贝到新数组即可
        for (int i = 0; i < N; i++) {
            eles[i] = temp[i];
        }
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
