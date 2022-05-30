package com.dream.algorithmsdemo.java.bubble;

import java.util.Arrays;

/**
 * function: 冒泡排序测试
 *
 * @author zy
 * @since 2022/5/29
 */
public class BubbleTest {

    public static void main(String[] args) {
        Integer[] arr = {4, 5, 6, 1, 2, 3};
        Bubble bubble = new Bubble();
        bubble.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

