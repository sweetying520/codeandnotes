package com.dream.algorithmsdemo.java.heap;

import java.sql.ResultSet;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/17
 */
public class HeapTest {

    public static void main(String[] args) {
        Heap<String> heap = new Heap<>(10);
        heap.insert("A");
        heap.insert("B");
        heap.insert("C");
        heap.insert("D");
        heap.insert("E");
        heap.insert("F");
        heap.insert("G");

        String delResult;
        while ((delResult = heap.delMax()) != null){
            System.out.print(delResult + " ");
        }
    }
}
