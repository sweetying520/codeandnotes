package com.dream.javabase.designpatterns.singleton.lazy;

/**
 * function: 懒汉式
 *
 * @author zy
 * @since 2022/11/26
 */
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton(){

    }

    public synchronized static LazySingleton getInstance(){
        if(instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }
}
