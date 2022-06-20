package com.dream.algorithmsdemo.java.linear.sequence;

/**
 * function: 顺序表测试
 *
 * @author zy
 * @since 2022/5/29
 */
public class SequenceTest {
    public static void main(String[] args) {
        SequenceList<String> s1 = new SequenceList<String>(3);
        s1.insert("姚明");
        s1.insert("科比");
        s1.insert("麦迪");
        s1.insert(1,"詹姆斯");

        //测试遍历
        for (String s : s1) {
            System.out.println(s + s1.length());
        }

//        //测试获取
//        String getResult = s1.get(1);
//        System.out.println("获取索引1处的结果为： " + getResult);
//        //测试删除
//        String removeResult = s1.remove(0);
//        System.out.println("删除的元素是：" + removeResult);
//        //测试清空
//        s1.clear();
//        System.out.println("清空后的元素个数为：" + s1.length());

    }
}
