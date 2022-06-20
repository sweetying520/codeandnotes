package com.dream.algorithmsdemo.java.linear.linkedlist;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/13
 */
public class TwoWayLinkListTest {

    public static void main(String[] args) {
        TwoWayLinkList<String> twoWayLinkList = new TwoWayLinkList<>();
        twoWayLinkList.insert("姚明");
        twoWayLinkList.insert("麦迪");
        twoWayLinkList.insert("科比");
        twoWayLinkList.insert(0,"詹姆斯");
        twoWayLinkList.insert(10,"erdai");
        for (String s : twoWayLinkList) {
            System.out.println(s);
        }

        System.out.println();
        System.out.println(twoWayLinkList.get(3));

        System.out.println();
        String remove = twoWayLinkList.remove(2);
        System.out.println(remove);

        System.out.println();
        twoWayLinkList.clear();
        System.out.println(twoWayLinkList.size());
    }
}
