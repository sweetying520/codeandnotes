package com.dream.algorithmsdemo.java.symboltable;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/6/14
 */
public class SymbolTableTest {

    public static void main(String[] args) {
        SymbolTable<Integer, String> symbolTable = new SymbolTable<>();

        symbolTable.put(1,"乔峰");
        symbolTable.put(2,"虚竹");
        symbolTable.put(3,"段誉");

        System.out.println(symbolTable.size());

        System.out.println();
        symbolTable.put(2,"慕容复");
        System.out.println(symbolTable.get(2));

        System.out.println();
        symbolTable.delete(1);
        System.out.println(symbolTable.size());
    }
}
