package com.dream.algorithmsdemo.java.tree;

import com.dream.algorithmsdemo.java.queue.Queue;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/17
 */
public class BinaryTreeErgodicTest {

    public static void main(String[] args) {
        BinaryTree<String, String> binaryTree = new BinaryTree<>();
        binaryTree.put("E","5");
        binaryTree.put("B","2");
        binaryTree.put("G","7");
        binaryTree.put("A","1");
        binaryTree.put("D","4");
        binaryTree.put("F","6");
        binaryTree.put("H","8");
        binaryTree.put("C","3");
        //前序
        Queue<String> strings = binaryTree.preErgodic();
        //结果 EBADCGFH
        for (String key : strings) {
            System.out.println(key + "========>" + binaryTree.get(key));
        }

        System.out.println();
        //中序
        //结果 ABCDEFGH
        Queue<String> strings1 = binaryTree.middleErgodic();
        for (String key : strings1) {
            System.out.println(key + "========>" + binaryTree.get(key));
        }

        System.out.println();
        //后序
        //结果 ACDBFHGE
        Queue<String> strings2 = binaryTree.afterErgodic();
        for (String key : strings2) {
            System.out.println(key + "========>" + binaryTree.get(key));
        }

        System.out.println();
        //层序：使用了广度优先的思想
        //结果 EBGADFHC
        Queue<String> strings3 = binaryTree.layerErgodic();
        for (String key : strings3) {
            System.out.println(key + "========>" + binaryTree.get(key));
        }

        System.out.println();
        //求最大深度
        System.out.println(binaryTree.maxDepth());
    }
}
