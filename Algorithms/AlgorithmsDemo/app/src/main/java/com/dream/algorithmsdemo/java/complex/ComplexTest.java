package com.dream.algorithmsdemo.java.complex;

/**
 * function: 通过求和，阶乘，反转来比较时间复杂度和空间复杂度
 *
 * @author zy
 * @since 2022/5/29
 */
public class ComplexTest {

    public static void main(String[] args) {
        //1、求和
//        sum();
        //2、阶乘
//        factorial();
        //3、反转
//        int[] reverse = reverse1(new int[]{1, 2, 3, 4, 5, 6, 9});
//        for (int j : reverse) {
//            System.out.println(j);
//        }
    }


    /**
     * 对指定的数组元素进行反转 时间复杂度O(n)
     */
    private static int[] reverse(int[] arr){
        int length = arr.length;
        int[] newArr = new int[length];
        for (int i = arr.length - 1; i >= 0; i--) {
            newArr[length - i - 1] = arr[i];
        }
        return newArr;
    }

    /**
     * 空间复杂度 O(1)
     */
    private static int[] reverse1(int[] aar){
        int length = aar.length;
        int temp;
        for(int start = 0,end = length - 1;start <= end;start++,end--){
            temp = aar[start];
            aar[start] = aar[end];
            aar[end] = temp;
        }
        return aar;
    }



    /**
     * 计算1.100的和  比较执行时间
     */
    private static void sum(){
        //一、计算1..100的和
        long startTime = System.currentTimeMillis();
        System.out.println("startTime: " + startTime);

        //1、法一：时间复杂度 O(n)
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum = sum + i;
        }
        System.out.println("方法一计算出来的值：" + sum);


        //2、法二：时间复杂度 O(1)
        int sum1;
        int n = 100;
        sum1 = (n+1)*n/2;
        System.out.println("方法二计算出来的值：" + sum1);


        long endTime = System.currentTimeMillis();
        System.out.println("endTime: " + endTime);
        long costTime = endTime - startTime;
        System.out.println("cost time：" + costTime + "ms");
    }

    /**
     * 计算10的阶乘 比较占用内存：占用 4 个字节
     */
    private static void factorial(){
        //二、计算10的阶乘

        //法一
        int sum = 1;
        for (int i = 1; i<= 10; i++) {
            sum *= i;
        }
        System.out.println(sum);

        //法二
        System.out.println(funcFactorial(10));

    }

    /**
     * 计算阶乘，占用内存多，重复调用方法需在栈中开辟内存去执行
     */
    private static int funcFactorial(int n){
        if(n == 1)return 1;
        return n * funcFactorial(n - 1);
    }
}


