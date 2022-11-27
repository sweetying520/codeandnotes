package com.dream.javabase.designpatterns.memento;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/27
 */
//1、创建一个备忘录类
class Memento{
    public String value;

    public Memento(String value) {
        this.value = value;
    }
}
//2、创建一个原始类
class Original{
    public String value;

    public Original(String value) {
        this.value = value;
    }

    public Memento createMemento(){
        return new Memento(value);
    }

    public void restoreMemento(Memento memento){
        this.value = memento.value;
    }
}

//3、创建一个存储备忘录的类
class Storage{
    public Memento memento;

    public Storage(Memento memento) {
        this.memento = memento;
    }
}

//4、测试
public class MementoClient {

    public static void main(String[] args) {
        //创建原始类
        Original original = new Original("erdai666");
        //创建一个存储类存储备忘录
        Storage storage = new Storage(original.createMemento());

        //修改原始类的状态
        original.value = "erdai";

        //回复原初始类的状态
        original.restoreMemento(storage.memento);
        System.out.println(original.value);

    }
}
