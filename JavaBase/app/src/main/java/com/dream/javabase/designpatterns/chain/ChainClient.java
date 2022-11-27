package com.dream.javabase.designpatterns.chain;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/27
 */

//1、定义一个责任链的抽象类
abstract class AbstractLogger{
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;
    protected int level;

    //责任链的下一个元素
    protected AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger){
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level,String message){
        if(this.level <= level){
            write(message);
        }

        if(nextLogger != null){
            nextLogger.logMessage(level,message);
        }
    }

    abstract protected void write(String message);
}

//2、创建实现类
class ConsoleLogger extends AbstractLogger{

    public ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger：" + message);
    }
}

class ErrorLogger extends AbstractLogger{

    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error::Logger：" + message);
    }
}


class FileLogger extends AbstractLogger{

    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger：" + message);
    }
}

//测试
public class ChainClient {

    private static AbstractLogger getChain(){
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);
        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger chain = getChain();

        chain.logMessage(AbstractLogger.INFO,"This is an information.");
        chain.logMessage(AbstractLogger.DEBUG,"This is a debug level information.");
        chain.logMessage(AbstractLogger.ERROR,"This is an error information.");
    }
}
