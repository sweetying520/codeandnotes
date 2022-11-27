package com.dream.javabase.designpatterns.adapter;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/26
 */
//1、定义一个需要被适配的类
class Adaptee{
    public void adaptee(){
        System.out.println("调用了被适配的方法");
    }
}

//2、定义一个目标接口
interface Target{
    void request();
}

//现在我们需要在目标接口的 request 方法中调用 Adaptee 的 adaptee 方法，如何实现呢？

//3、定义一个适配器的类
class Adapter extends Adaptee implements Target{

    @Override
    public void request(){
        System.out.println("通过适配器，连接目标方法");
        super.adaptee();
    }
}

public class ClassClient {

    //测试
    public static void main(String[] args){
        Target target = new Adapter();
        target.request();
    }
}

