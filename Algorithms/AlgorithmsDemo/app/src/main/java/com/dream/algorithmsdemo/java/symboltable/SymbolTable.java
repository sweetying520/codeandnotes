package com.dream.algorithmsdemo.java.symboltable;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/14
 */
public class SymbolTable<Key,Value>{

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

    public SymbolTable() {
        head = new Node(null, null, null);
        N = 0;
    }

    public void put(Key key,Value value){
        //如果已经存在key，找到该节点替换值即可
        Node temp = head;
        while (temp.next != null){
            temp = temp.next;
            if(temp.key.equals(key)){
                temp.value = value;
                return;
            }
        }
        //如果不存在，创建新节点，插入值
        Node newNode = new Node(key, value, null);
        Node oldFirst = head.next;
        newNode.next = oldFirst;
        head.next = newNode;
        //元素 +1
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
