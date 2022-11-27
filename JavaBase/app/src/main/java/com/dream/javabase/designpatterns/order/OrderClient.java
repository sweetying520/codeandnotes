package com.dream.javabase.designpatterns.order;

import java.util.ArrayList;
import java.util.List;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/27
 */

//1、创建一个命令接口
interface Order{
    void execute();
}

//2、创建一个请求类
class Stock{
    private String name = "ABC";
    private int quantity = 10;

    public void buy(){
        System.out.println("buy，name：" + name + " quantity：" + quantity);
    }

    public void sell(){
        System.out.println("sell，name：" + name + " quantity：" + quantity);
    }
}

//3、常见命令的实现类，实现不同的命令
class BuyStock implements Order{

    private final Stock stock;

    public BuyStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.buy();
    }
}

class SellStock implements Order{

    private final Stock stock;

    public SellStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.sell();
    }
}

//4、创建命令的调用类
class Broker{

    private List<Order> orderList = new ArrayList<>();

    public void takeOrder(Order order){
        orderList.add(order);
    }

    public void placeOrders(){
        for (Order order : orderList) {
            order.execute();
        }
    }
}

//5、测试
public class OrderClient {
    public static void main(String[] args) {
        Stock stock = new Stock();

        BuyStock buyStock = new BuyStock(stock);
        SellStock sellStock = new SellStock(stock);

        Broker broker = new Broker();
        broker.takeOrder(buyStock);
        broker.takeOrder(sellStock);

        broker.placeOrders();
    }
}
