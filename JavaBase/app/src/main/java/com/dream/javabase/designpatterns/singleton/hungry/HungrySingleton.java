package com.dream.javabase.designpatterns.singleton.hungry;

/**
 * function: 饿汉式
 *
 * @author zy
 * @since 2022/11/26
 */
public class HungrySingleton {

    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton(){

    }

    public static HungrySingleton getInstance(){
        return instance;
    }
}
