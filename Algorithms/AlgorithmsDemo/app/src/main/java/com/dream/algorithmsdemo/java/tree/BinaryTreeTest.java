package com.dream.algorithmsdemo.java.tree;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/17
 */
public class BinaryTreeTest {

    public static void main(String[] args) {
        BinaryTree<Integer, String> binaryTree = new BinaryTree<>();
        binaryTree.put(1,"张三");
        binaryTree.put(2,"李四");
        binaryTree.put(3,"王五");
        for (int i = 1; i <= binaryTree.size(); i++) {
            System.out.println(binaryTree.get(i));
        }

        binaryTree.delete(2);
        System.out.println(binaryTree.size());
    }
}
