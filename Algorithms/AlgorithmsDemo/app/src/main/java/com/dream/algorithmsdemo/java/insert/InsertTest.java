package com.dream.algorithmsdemo.java.insert;

import java.util.Arrays;

/**
 * function: 插入排序测试
 *
 * @author zy
 * @since 2022/5/29
 */
public class InsertTest {

    public static void main(String[] args) {
        Integer[] arr = {4, 3, 2, 10, 12, 1, 5, 6};
        Insert insert = new Insert();
        insert.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
