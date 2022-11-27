package com.dream.javabase.designpatterns.bridge;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/27
 */

//1、创建画颜色的接口：桥接接口
interface DrawAPI{
    void drawColor();
}

//2、创建实现类
class RedDraw implements DrawAPI{
    @Override
    public void drawColor(){
        System.out.println("red color");
    }
}

class GreenDraw implements DrawAPI{
    @Override
    public void drawColor(){
        System.out.print("green color");
    }
}

//3、定义一个抽象类 Shape（形状）：持有 DrawAPI 的 引用
abstract class Shape{
    protected DrawAPI drawAPI;

    public Shape(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}

//4、实现类，画具体的形状，并添加颜色
class Circle extends Shape{

    public Circle(DrawAPI drawAPI) {
        super(drawAPI);
    }

    @Override
    public void draw() {
        System.out.print("draw Circle with ");
        drawAPI.drawColor();
    }
}


class Rectangle extends Shape{

    public Rectangle(DrawAPI drawAPI) {
        super(drawAPI);
    }

    @Override
    public void draw() {
        System.out.print("draw Rectangle with ");
        drawAPI.drawColor();
    }
}


public class BridgeClient {

    public static void main(String[] args) {
        Shape redCircle = new Circle(new RedDraw());
        Shape greenRectangle = new Rectangle(new GreenDraw());

        redCircle.draw();
        greenRectangle.draw();
    }
}
