package com.dream.javabase.designpatterns.interpret;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/27
 */
//1、创建一个解释器的接口
interface Expression{
    boolean interpret(String context);
}

//2、创建实现类
class TerminalExpression implements Expression{
    private final String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        return context.contains(data);
    }
}

class OrExpression implements Expression{

    private Expression expression1;
    private Expression expression2;

    public OrExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public boolean interpret(String context) {
        return expression1.interpret(context) || expression2.interpret(context);
    }
}

class AndExpression implements Expression{

    private Expression expression1;
    private Expression expression2;

    public AndExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public boolean interpret(String context) {
        return expression1.interpret(context) && expression2.interpret(context);
    }
}


public class InterpretClient {

    //规则：小明和二代都是男的
    public static Expression getMaleExpression(){
        Expression xiaoming = new TerminalExpression("小明");
        Expression erdai = new TerminalExpression("二代");
        return new OrExpression(xiaoming, erdai);
    }

    //规则：小红是已婚的
    public static Expression getMarriedWomenExpression(){
        Expression xiaohong = new TerminalExpression("小红");
        Expression married = new TerminalExpression("已婚");
        return new AndExpression(xiaohong, married);
    }

    public static void main(String[] args) {
        Expression isMale = getMaleExpression();
        Expression isMarriedWomen = getMarriedWomenExpression();

        System.out.println("二代是男的？：" + isMale.interpret("二代"));
        System.out.println("小红是否已经结婚了？：" + isMarriedWomen.interpret("小红已婚"));
    }
}
