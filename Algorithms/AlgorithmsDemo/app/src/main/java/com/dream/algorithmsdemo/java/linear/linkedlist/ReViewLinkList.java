package com.dream.algorithmsdemo.java.linear.linkedlist;

import androidx.annotation.NonNull;

import java.util.Iterator;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/13
 */
@SuppressWarnings("UnnecessaryLocalVariable")
public class ReViewLinkList<T> implements Iterable<T> {

    //记录头节点
    private Node head;
    //记录链表的长度
    private int N;

    private class Node{
        private T item;
        private Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public ReViewLinkList() {
        head = new Node(null,null);
        N = 0;
    }

    public void clear(){
        head.next = null;
        N = 0;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public T get(int i){
        if(i >= N) return null;
        //我们需要从头节点的下一个节点开始找，这个节点为第0个元素
        Node temp = head.next;
        //for 循环 j < i ，例如 i == 1，那么 for 循环就会走一次，此时获取的便是第一个元素
        for (int j = 0; j < i; j++) {
            temp = temp.next;
        }
        return temp.item;
    }

    public void insert(T t){
        //寻找最后一个节点
        Node temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        //创建一个新节点
        Node newNode = new Node(t,null);
        //将最后一个节点的next指向新节点
        temp.next = newNode;
        //元素个数 + 1
        N++;

    }

    public void insert(int index,T t){
        if(index >= N)return;
        //index 的 前一个节点
        Node pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        //当前节点
        Node current = pre.next;
        //创建一个新节点
        Node newNode = new Node(t, null);
        pre.next = newNode;
        newNode.next = current;
        //元素个数 + 1
        N++;
    }

    public T remove(int index){
        if(index >= N) return null;
        Node pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node current = pre.next;
        pre.next = current.next;
        N--;
        return current.item;
    }


    public int indexOf(T t){
        Node temp = head.next;
        int index = -1;
        while (temp != null){
            index++;
            if(temp.item.equals(t)){
                return index;
            }
            temp = temp.next;
        }
        return -1;
    }


    @NonNull
    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T>{

        private Node n;

        public MyIterator() {
            n = head;
        }

        @Override
        public boolean hasNext() {
            return n.next != null;
        }

        @Override
        public T next() {
            n = n.next;
            return n.item;
        }
    }
}
