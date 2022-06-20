package com.dream.algorithmsdemo.java.symboltable;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/14
 */
public class OrderSymbolTable<Key extends Comparable<Key>,Value>{

    private Node head;
    private int N;

    private class Node{
        public Key key;
        public Value value;
        public Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public OrderSymbolTable() {
        head = new Node(null, null, null);
        N = 0;
    }

    public void put(Key key,Value value){
        //定义两个变量，当前节点，前一个节点
        Node pre = head;
        Node curr = head.next;
        if(curr == null)return;
        while (key.compareTo(curr.key) > 0){
            pre = curr;
            curr = curr.next;
        }


        //如果存在相同的键，则将值替换
        if(key.compareTo(curr.key) == 0){
            curr.value = value;
            return;
        }

        //如果不存在
        Node newNode = new Node(key, value, null);
        pre.next = newNode;
        newNode.next = curr;
        N++;
    }


    public void delete(Key key){
        Node temp = head;
        while (temp.next != null){
            if(temp.next.key.equals(key)){
                temp.next = temp.next.next;
                N--;
                return;
            }
            temp = temp.next;
        }
    }

    public Value get(Key key){
        Node temp = head;
        while (temp.next != null){
            temp = temp.next;
            if(temp.key.equals(key)){
                return temp.value;
            }
        }
        return null;
    }

    public int size(){
        return N;
    }
}
