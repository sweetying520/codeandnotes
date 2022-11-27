package com.dream.javabase.designpatterns.adapter;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/26
 */
//1、定义一个电压的接口
interface AC{
    int outputAC();
}

//2、定义一个 220V 的实现类
class AC220 implements AC{

    @Override
    public int outputAC(){
        return 220;
    }
}

//3、适配器接口，outputDC5V 方法用于将输入的电压变为 5V 后输出
interface DC5Adapter{
    int outputDC5V(AC ac);
}

//4、实现电源适配器
class PowerAdapter implements DC5Adapter{

    @Override
    public int outputDC5V(AC ac){
        int outputAC = ac.outputAC();
        //变压器
        int adapterOutput = outputAC / 44;
        System.out.println("收到：" + outputAC + "V的电压，通过适配器转换，输出为：" + adapterOutput + "V");
        return adapterOutput;
    }
}

public class ObjectClient {

    public static void main(String[] args) {
        DC5Adapter adapter = new PowerAdapter();
        AC ac = new AC220();
        adapter.outputDC5V(ac);
    }
}
