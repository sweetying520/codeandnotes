package com.dream.algorithmsdemo.java.bubble;

/**
 * function: 冒泡排序
 *
 * @author zy
 * @since 2022/5/29
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class Bubble {

    //最坏的情况O(n^2)
    public void sort(Comparable[] a){
        int length = a.length;
        for(int i = length - 1;i > 0; i--){
            for (int j = 0;j < i;j++){
                //比较索引 j 和 j + 1 的值
                if(greater(a[j],a[j + 1])){
                    exchange(a,j,j+1);
                }
            }
        }
    }


    private boolean greater(Comparable v,Comparable w){
        return v.compareTo(w) > 0;
    }


    private void exchange(Comparable[] a,int i,int j){
        Comparable temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
