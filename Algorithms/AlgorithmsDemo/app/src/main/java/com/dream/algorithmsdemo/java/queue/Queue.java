package com.dream.algorithmsdemo.java.queue;

import androidx.annotation.NonNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/14
 */
public class Queue<T> implements Iterable<T>{

    private Node head;
    private Node last;
    private int N;


    private class Node{
        public T item;
        public Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public Queue(){
        head = new Node(null, null);
        last = null;
        N = 0;
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public void enQueue(T item){
        //当前尾节点为null
        if(last == null){
            last = new Node(item, null);
            head.next = last;
        }else {
            Node oldLast = last;
            Node newNode = new Node(item, null);
            oldLast.next = newNode;
            last = newNode;
        }
        N++;
    }

    public T deQueue(){
        Node temp = head.next;
        if(temp == null) return null;
        head.next = temp.next;
        N--;
        //因为出队列其实是在删除元素，因此如果删除完了，需要重置 last 为null
        if(isEmpty()){
            last = null;
        }
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
