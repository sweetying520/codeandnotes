package com.dream.algorithmsdemo.java;

import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/5/30
 */
public class Solution {

    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 4, 5, 6, 7};
//        rotate(arr,3);
//        System.out.println(Arrays.toString(arr));
//        System.out.println('l' - 'a');
//        firstUniqChar("leetcode");
        //System.out.println('4' - '0');
        System.out.println("er".substring(0,1));
    }


    public static void testArrayDeque(){
        //栈功能
//        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
//        arrayDeque.push(1);
//        arrayDeque.push(2);
//        arrayDeque.push(3);
//        arrayDeque.push(4);
//        arrayDeque.push(5);
//        while (!arrayDeque.isEmpty()){
//            System.out.println(arrayDeque.pop());
//        }

        //队列功能
//        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
//        arrayDeque.add(1);
//        arrayDeque.add(2);
//        arrayDeque.add(3);
//        arrayDeque.add(4);
//        arrayDeque.add(5);
//        for (Integer integer : arrayDeque) {
//            System.out.println(integer);
//        }

//        while (!arrayDeque.isEmpty()){
//            System.out.println(arrayDeque.poll());
//        }

    }


    public static void testHeap(){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(5);
        priorityQueue.add(4);
        priorityQueue.add(3);
        priorityQueue.add(2);
        priorityQueue.add(1);
        System.out.println(priorityQueue.peek());
        while (priorityQueue.size() > 0){
            System.out.println(priorityQueue.poll());
        }


    }
    //排序
    public int findKthLargest(int[] nums, int k) {
        //优先级队列，就是个小根堆
        //固定优先级队列的长度为k,那么最后留在优先级队列里面的数就是最大的k个数。
        PriorityQueue<Integer> minTree = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            //每个数都加入到小根堆中。
            minTree.add(nums[i]);
            //如果加入之后堆的大小 大于k，那么就弹出堆顶。
            //这样的话每次加入之后最大的都在下面，弹出的都是小的数。
            if (minTree.size() > k) minTree.poll();
        }
        //堆顶的数就是第k大的。
        return minTree.peek();
    }

    public static int myAtoi(String s) {
        //1、把空格都去掉
        s = s.trim();
        //2、处理符号符号
        int index = 0;
        int sign = 1;
        if(s.charAt(index) == '-' || s.charAt(index) == '+'){
            sign = s.charAt(index++) == '+' ? 1 : -1;
        }
        int res = 0;
        //3、读数为数字的字符，其余的忽略掉
        for (; index < s.length(); index++) {
            int digit = s.charAt(index) - '0';
            if(digit < 0 || digit > 9){
                //证明不是数字了
                break;
            }

            //越界处理
            if(res > Integer.MAX_VALUE / 10
                    || (res == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)){
                res = sign == -1? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }else{
                res = res * 10 + digit;
            }
        }
        return sign * res;
    }

    public static int firstUniqChar(String s) {
        char c = s.charAt(1);
        int count[] = new int[26];
        char[] chars = s.toCharArray();
        //先统计每个字符出现的次数
        for (int i = 0; i < s.length(); i++)
            count[chars[i] - 'a']++;
        //然后在遍历字符串s中的字符，如果出现次数是1就直接返回
        for (int i = 0; i < s.length(); i++)
            if (count[chars[i] - 'a'] == 1)
                return i;
        return -1;
    }



    public void reverseString(char[] s) {
       int i = 123;
        char[] chars = String.valueOf(i).toCharArray();
    }

    private void swap(char[] s,int i,int j){
        s[i] ^= s[j];
        s[j] ^= s[i];
        s[i] ^= s[j];
    }

    public static void rotate(int[] nums, int k) {
        List<Integer> integerList = new ArrayList<>();
        int length = nums.length;
        k %= length;
        int[] temp = new int[k];
        Map<Integer,Integer> integerMap = new HashMap<>();
        for (int j = 0, i = length - k; i < length; i++, j++) {
            temp[j] = nums[i];
        }
        for (int i = length - k - 1; i >= 0; i--) {
            nums[i + k] = nums[i];
        }

        for (int i = 0; i < k; i++) {
            nums[i] = temp[i];
        }
    }


    private static int fibonacci(int n){
        if(n == 1 || n == 2){
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n -2);
    }


    // [-4,-1,-1,0,1,2]
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 3){
            return res;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for(int i = 0; i < length;i++){
            if(nums[i] > 0){
                break;
            }

            if(i != 0 && nums[i] == nums[i-1])continue;
            int l = i + 1;
            int r = length - 1;
            while (l < r){
                int sum = nums[i] + nums[l] + nums[r];
                if(sum == 0){
                    res.add(Arrays.asList(nums[i],nums[l],nums[r]));
                    while (l < r && nums[l] == nums[l+1]){
                        l++;
                    }
                    while (l < r && nums[r] == nums[r-1]){
                        r--;
                    }
                    l++;
                    r--;
                }else if(sum < 0){
                    l++;
                }else {
                    r--;
                }
            }
        }
        return res;
    }


}
