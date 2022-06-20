package com.dream.algorithmsdemo.java.linear.linkedlist;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/2
 */
public class LinkList<T> {

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

    public LinkList() {
        //初始化头节点
        head = new Node(null, null);
        //初始化元素个数
        this.N = 0;
    }

    public void clear(){
        //将当前头节点的next断掉，那么就找不到这条链表了
        head.next = null;
        //将链表的长度设置为0
        this.N = 0;
    }

    public boolean isEmpty(){
        return this.N == 0;
    }

    public int length(){
        return N;
    }

    public T get(int i){
        //从第 0 个元素开始找，找 i 次，就是第 i 个节点的数据
        Node node = head.next;
        if(node == null) return null;
        for (int i1 = 0; i1 < i; i1++) {
            node = node.next;
        }
        if(node == null) return null;
        return node.item;
    }

    public void insert(T t){
        Node node = head;
        while (node.next != null){
            node = node.next;
        }
        //执行到这里，说明到了尾节点
        //构建一个新的节点
        @SuppressWarnings("UnnecessaryLocalVariable")
        Node newNode= new Node(t,null);
        //将尾巴节点的next指向它
        node.next = newNode;
        //链表长度 +1
        N++;
    }

    public void insert(int index,T t){
        //找到 i 的前一个节点和当前 i 节点
        Node pre = head;
        for (int i = 0; i <= index - 1; i++) {
            pre = pre.next;
        }
        Node current = pre.next;
        //创建新的节点，将前一个节点指向插入的节点，当前插入节点指向下一个节点
        @SuppressWarnings("UnnecessaryLocalVariable")
        Node newNode = new Node(t, current);
        pre.next = newNode;
        //元素加一
        N++;
    }

    public T remove(int index){
        //找到当前 i 的前一个节点
        Node pre = head;
        for (int i = 0; i <= index - 1; i++) {
            pre = pre.next;
        }
        //找到当前节点
        Node current = pre.next;
        //将前一个节点的next指向当前节点的next
        pre.next = current.next;
        //元素个数减一
        N--;
        return current.item;
    }


    public int indexOf(T t){
        int index = 0;
        Node node = head.next;
        while (node != null){
            if(node.item.equals(t)){
                return index;
            }
            node = node.next;
            index++;
        }
        return -1;
    }
}
