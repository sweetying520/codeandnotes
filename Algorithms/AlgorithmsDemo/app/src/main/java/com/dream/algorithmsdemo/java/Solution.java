package com.dream.algorithmsdemo.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

        myAtoi("  -42");
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

}
