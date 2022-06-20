package com.dream.algorithmsdemo.java.linear.linkedlist;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/13
 */
public class ReviewLinkListTest {

    public static void main(String[] args) {
        ReViewLinkList<Integer> integerLinkList = new ReViewLinkList<>();
        integerLinkList.insert(2);
        integerLinkList.insert(3);
        integerLinkList.insert(4);
        integerLinkList.insert(0,1);
        integerLinkList.insert(8,10);
        for (int i = 0; i < integerLinkList.size(); i++) {
            System.out.println(integerLinkList.get(i));
        }


        System.out.println();
        Integer remove = integerLinkList.remove(1);
        System.out.println(remove);

        System.out.println(integerLinkList.get(5));
        System.out.println(integerLinkList.indexOf(20));
    }
}
