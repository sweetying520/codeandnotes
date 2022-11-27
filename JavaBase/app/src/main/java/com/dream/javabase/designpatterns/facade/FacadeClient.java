package com.dream.javabase.designpatterns.facade;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/27
 */
//1、提供一些子系统的类
class CPU{
    public void startup(){
        System.out.println("cpu startup");
    }

    public void shutdowm(){
        System.out.println("cpu shutdowm");
    }
}

class RAM{
    public void startup(){
        System.out.println("ram startup");
    }

    public void shutdowm(){
        System.out.println("ram shutdowm");
    }
}

class ROM{
    public void startup(){
        System.out.println("rom startup");
    }

    public void shutdowm(){
        System.out.println("rom shutdowm");
    }
}

//2、外观类
class Computer{
    private CPU cpu;
    private RAM ram;
    private ROM rom;

    public Computer(){
        cpu = new CPU();
        ram = new RAM();
        rom = new ROM();
    }

    public void startup(){
        cpu.startup();
        ram.startup();
        rom.startup();
    }

    public void shutdowm(){
        cpu.shutdowm();
        ram.shutdowm();
        rom.shutdowm();
    }
}




public class FacadeClient {

    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startup();
        computer.shutdowm();
    }
}
