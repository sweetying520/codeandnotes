package com.dream.algorithmsdemo.java.select;

import java.util.Arrays;

/**
 * function: 选择排序测试
 *
 * @author zy
 * @since 2022/5/29
 */
public class SelectionTest {

    public static void main(String[] args) {
        Integer[] arr = {4, 6, 8, 7, 9, 2, 10, 1};
        Selection selection = new Selection();
        selection.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
