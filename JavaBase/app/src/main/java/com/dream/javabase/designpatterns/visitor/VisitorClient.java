package com.dream.javabase.designpatterns.visitor;

import org.intellij.lang.annotations.Subst;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/27
 */

//1、创建待访问的元素对象接口
interface Subject{
    void accept(Visitor visitor);
    String getSubject();
}
//2、创建访问者接口
interface Visitor{
    void visit(Subject sub);
}

//3、创建各自的实现类
class MySubject implements Subject{

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getSubject() {
        return "love";
    }
}

class MyVisitor implements Visitor{

    @Override
    public void visit(Subject sub) {
        System.out.println("visit the subject：" + sub.getSubject());
    }
}

//4、测试
public class VisitorClient {
    public static void main(String[] args) {
        Subject subject = new MySubject();
        Visitor visitor = new MyVisitor();
        subject.accept(visitor);
    }
}
