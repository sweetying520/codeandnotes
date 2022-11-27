package com.dream.javabase.designpatterns.template;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/27
 */


//1、抽象类：定义了一套算法框架
abstract class Game{
    abstract void init();
    abstract void startPlay();
    abstract void endPlay();

    //模版方法
    public final void play(){
        //初始化游戏
        init();
        //开始游戏
        startPlay();
        //结束游戏
        endPlay();
    }
}

//2、定义实现类
class LOL extends Game{

    @Override
    void init() {
        System.out.println("LOL initialized！start play...");
    }

    @Override
    void startPlay() {
        System.out.println("LOL started，enjoy it...");
    }

    @Override
    void endPlay() {
        System.out.println("LOL finished...");
    }
}

public class TemplateClient {
    public static void main(String[] args) {
        Game game = new LOL();
        game.play();
    }
}
