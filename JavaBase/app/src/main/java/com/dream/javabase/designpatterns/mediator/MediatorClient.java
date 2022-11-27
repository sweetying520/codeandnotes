package com.dream.javabase.designpatterns.mediator;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/27
 */
//1、创建一个统一行为的接口
interface Mediator{
    void showMessage(String message);
}

//2、创建实现类
class User implements Mediator{
    @Override
    public void showMessage(String message){
        System.out.println("User：" + message);
    }
}

//3、创建中介类
class ChatRoom implements Mediator{

    @Override
    public void showMessage(String message) {
        (new User()).showMessage(message);
    }
}

//4、测试
public class MediatorClient {
    public static void main(String[] args) {
        Mediator room = new ChatRoom();
        room.showMessage("Hi，erdai");
    }
}
