package com.dream.algorithmsdemo.java.heap;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/17
 */
public class Heap<T extends Comparable<T>> {

    private T[] items;
    private int N;

    public Heap(int capacity){
        items = (T[]) new Comparable[capacity + 1];
        N = 0;
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

    //使用上浮算法，使索引 k 处的元素能在堆中处于一个正确的位置
    private void swim(int k){
        //通过循环，不断比较当前节点和父节点的值，如果父节点比当前节点小则交换位置
        while (k > 1){
            //比较当前节点和父节点
            if(less(k/2,k)){
                exch(k/2,k);
            }
            k = k / 2;
        }
    }

    //删除堆中的最大元素，并返回这个元素
    public T delMax(){
        T max = items[1];
        //交换1和最大索引处的位置
        exch(1,N);
        //将最大索引处的位置置为 null
        items[N] = null;
        //元素个数减1
        N--;
        //让堆重新有序，通过下沉调整堆
        sink(1);
        return max;
    }

    private void sink(int k){
        //循环，不断对比当前 k 和其子节点 2k 及 2k+1 中的较大者的元素大小，如果当前节点小，则交换位置
        while (2*k <= N){
            //获取当前节点的子节点的较大者
            int max;
            if(2*k + 1 < N){
                //证明有右子节点
                if(less(2*k,2*k + 1)){
                    //左子节点小于右子节点
                    max = 2*k + 1;
                }else {
                    //左子节点大于右子节点
                    max = 2*k;
                }
            }else {
                max = 2*k;
            }
            //比较当前节点和较大者的值
            if(!less(k,max)){
                break;
            }

            //交换 k 和 max 的位置
            exch(k,max);

            k = max;
        }
    }
}
