package com.dream.javabase.designpatterns.singleton.static_inner_class;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/26
 */
public class StaticInnerClassSingleton {

    private StaticInnerClassSingleton(){

    }

    private static final class Holder{
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance(){
        return Holder.INSTANCE;
    }
}
