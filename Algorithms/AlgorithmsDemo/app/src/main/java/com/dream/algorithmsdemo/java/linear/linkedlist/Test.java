package com.dream.algorithmsdemo.java.linear.linkedlist;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/3
 */
public class Test {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, null);
        ListNode first = new ListNode(2, null);
        ListNode second = new ListNode(3, null);
        ListNode third = new ListNode(4, null);
        ListNode fourth = new ListNode(5, null);
        head.next = first;
        first.next = second;
        second.next = third;
        third.next = fourth;

        ListNode listNode = removeNthFromEnd(head, 2);
        while (listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = head;
        //先求出链表的长度
        int length = 1;
        while(temp != null){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
            length++;
        }
        int num = length - n;
        ListNode pre = head;
        if(num <= 0){
            pre.val = pre.next.val;
            pre.next = pre.next.next;
        }else {
            for(int i = 0; i <= num - 2; i++){
                pre = pre.next;
            }
            ListNode current = pre.next;
            pre.next = current.next;
        }
        return head;
    }

}
