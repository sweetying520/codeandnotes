package com.dream.algorithmsdemo.java.symboltable;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/14
 */
public class OrderSymbolTableTest {

    public static void main(String[] args) {
        OrderSymbolTable<Integer, String> symbolTable = new OrderSymbolTable<>();

        symbolTable.put(1,"张三");
        symbolTable.put(2,"李四");
        symbolTable.put(4,"赵六");
        symbolTable.put(7,"田七");

        symbolTable.put(3,"王五");
    }
}
