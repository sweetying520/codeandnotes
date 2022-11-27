package com.dream.javabase.designpatterns.singleton.double_check;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/26
 */
public class DoubleCheckSingleton {

    private volatile static DoubleCheckSingleton instance;

    private DoubleCheckSingleton(){

    }

    public static  DoubleCheckSingleton getInstance(){
        if(instance == null){
            synchronized (DoubleCheckSingleton.class){
                if(instance == null){
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
