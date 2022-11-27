package com.dream.javabase.designpatterns.adapter;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/26
 */

//1、定义一个接口，里面有两个抽象方法
interface ITest{
    void test();
    void test1();
}

//2、定义一个适配器的抽象类
abstract class AdapterWrapper implements ITest{

    @Override
    public void test() {

    }

    @Override
    public void test1() {

    }
}

//3、接下来，我们就可以继承适配器的抽象类，然后去选择需要实现的方法
class Test extends AdapterWrapper{
    @Override
    public void test() {
        System.out.println("erdai666");
    }
}

public class InterfaceClient {

    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }
}