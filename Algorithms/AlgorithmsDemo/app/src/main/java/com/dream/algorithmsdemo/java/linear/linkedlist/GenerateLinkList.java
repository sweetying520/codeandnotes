package com.dream.algorithmsdemo.java.linear.linkedlist;

/**
 * function: 生成列表
 *
 * @author zy
 * @since 2022/6/13
 */
public class GenerateLinkList {

    @SuppressWarnings("rawtypes")
    public static class Node<T>{
        public T item;
        public Node next;

        public Node(T item,Node next){
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        //构建节点
        Node<Integer> first = new Node<>(11,null);
        Node<Integer> second = new Node<>(13,null);
        Node<Integer> third = new Node<>(12,null);
        Node<Integer> fourth = new Node<>(8,null);
        Node<Integer> five = new Node<>(9,null);

        //生成链表
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = five;
    }
}
