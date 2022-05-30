package com.dream.algorithmsdemo.java.select;

/**
 * function: 选择排序
 *
 * @author zy
 * @since 2022/5/29
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class Selection {

    public void sort(Comparable[] arr){
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if(greater(arr[minIndex],arr[j])){
                    minIndex = j;
                }
            }

            if(minIndex != 0){
                exchange(arr,i,minIndex);
            }
        }
    }


    private Boolean greater(Comparable a,Comparable b){
        return a.compareTo(b) > 0;
    }

    private void exchange(Comparable[] a,int i,int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
