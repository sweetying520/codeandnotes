package com.dream.algorithmsdemo.java.stack;

import androidx.annotation.NonNull;

import java.util.Iterator;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/14
 */
@SuppressWarnings("UnnecessaryLocalVariable")
public class Stack<T> implements Iterable<T>{

    //头节点
    private Node head;
    //记录元素个数
    private int N;

    public Stack() {
        head = new Node(null, null);
        N = 0;
    }



    private class Node{
        public Node next;
        public T item;

        public Node(Node next, T item) {
            this.next = next;
            this.item = item;
        }
    }


    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void clear(){
        head.next = null;
        N = 0;
    }

    public void push(T item){
        Node temp = head.next;
        Node newNode = new Node(null, item);
        head.next = newNode;
        newNode.next = temp;
        N++;
    }

    public T pop(){
        Node temp = head.next;
        if(temp == null)return null;
        head.next = temp.next;
        N--;
        return temp.item;
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T>{

        private Node temp;

        public MyIterator() {
            temp = head;
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
