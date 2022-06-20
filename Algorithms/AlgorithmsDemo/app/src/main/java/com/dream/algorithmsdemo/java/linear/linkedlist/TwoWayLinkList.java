package com.dream.algorithmsdemo.java.linear.linkedlist;


import androidx.annotation.NonNull;

import java.util.Iterator;

/**l
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/13
 */
@SuppressWarnings("UnnecessaryLocalVariable")
public class TwoWayLinkList<T> implements Iterable<T>{

    //记录头节点
    private Node first;
    //记录尾节点
    private Node last;
    //记录链表的长度
    private int N;


    private class Node{
        private T item;
        private Node pre;
        private Node next;

        public Node(T item,Node pre,Node next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }

    public TwoWayLinkList() {
        first = new Node(null, null, null);
        last = null;
        N = 0;
    }

    public void clear(){
        first.next = null;
        last = null;
        N = 0;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public T getFirst(){
        if(isEmpty()) return null;
        return first.next.item;
    }

    public T getLast(){
        if(isEmpty())return null;
        return last.item;
    }

    public T get(int i){
        Node temp = first.next;
        for (int j = 0; j < i; j++) {
            temp = temp.next;
        }
        return temp.item;
    }

    public void insert(T t){
        if(isEmpty()){
            //创建一个新节点
            Node newNode = new Node(t, first, null);
            //将其赋值给 last
            last = newNode;
            //头节点的next指向尾节点即可
            first.next = last;
        }else {
            Node oldLast = last;
            Node newNode = new Node(t, oldLast, null);
            oldLast.next = newNode;
            last = newNode;
        }
        N++;
    }

    public void insert(int index,T t){
        if(index >= N)return;
        Node pre = first;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node current = pre.next;
        Node newNode = new Node(t, pre, current);
        pre.next = newNode;
        current.pre = newNode;
        N++;
    }

    public int indexOf(T t){
        Node temp = first;
        for (int i = 0;temp.next != null;i++){
            temp = temp.next;
            if(temp.next.item.equals(t)){
                return i;
            }
        }
        return -1;
    }

    public T remove(int index){
        if(index >= N) return null;
        Node pre = first;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node current = pre.next;
        Node nextNode = current.next;
        pre.next = nextNode;
        nextNode.pre = pre;
        N--;
        return current.item;
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T>{

        private Node temp;

        public MyIterator() {
            temp = first;
        }

        @Override
        public boolean hasNext() {
            return temp.next != null;
        }

        @Override
        public T next() {
            temp = temp.next;
            return temp.item;
        }
    }
}
