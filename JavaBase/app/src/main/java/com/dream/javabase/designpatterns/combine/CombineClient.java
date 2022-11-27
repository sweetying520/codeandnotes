package com.dream.javabase.designpatterns.combine;

import java.util.ArrayList;
import java.util.List;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/27
 */

//1、定义一个抽象类
abstract class Component{
    public abstract void operation();

    public void add(Component component){

    }

    public void remove(Component component){

    }
}

//2、定义一组相似的实现类
class ComponentImpl1 extends Component{

    @Override
    public void operation() {
        System.out.println("ComponentImpl1 operation");
    }
}

class ComponentImpl2 extends Component{

    @Override
    public void operation() {
        System.out.println("ComponentImpl2 operation");
    }
}

class Combine extends Component{

    private final List<Component> list = new ArrayList<>();

    @Override
    public void operation() {
        for (Component component : list) {
            component.operation();
        }
    }

    @Override
    public void add(Component component) {
        list.add(component);
    }

    @Override
    public void remove(Component component) {
        list.remove(component);
    }
}

public class CombineClient {

    public static void main(String[] args) {
        Component c1 = new ComponentImpl1();
        Component c2 = new ComponentImpl2();
        Component combine = new Combine();
        combine.add(c1);
        combine.add(c2);
        combine.operation();
        combine.remove(c2);
        combine.operation();
    }
}
