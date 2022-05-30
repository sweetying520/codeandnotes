package com.dream.algorithmsdemo.java.insert;

/**
 * function: 插入排序
 *
 * @author zy
 * @since 2022/5/29
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class Insert {


    //时间复杂度：O(n^2)
    public void sort(Comparable[] arr){
        //
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0; j--) {
                if (greater(arr[j - 1], arr[j])) {
                    exchange(arr, j - 1, j);
                }else {
                    break;
                }
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
