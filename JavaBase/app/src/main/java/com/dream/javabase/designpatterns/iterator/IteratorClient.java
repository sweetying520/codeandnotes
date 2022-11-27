package com.dream.javabase.designpatterns.iterator;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/27
 */

//1、创建接口
interface Iterator{
    boolean hasNext();
    Object next();
}

interface Container{
    Iterator getIterator();
}

//2、创建实现类
class NameRepository implements Container{
    String[] names = {"小明","小红","二代"};

    @Override
    public Iterator getIterator() {
        return new NameIterator();
    }


    private class NameIterator implements Iterator{

        int index;

        @Override
        public boolean hasNext() {
            return index < names.length;
        }

        @Override
        public Object next() {
            if(hasNext()){
                return names[index++];
            }
            return null;
        }
    }
}

//3、测试
public class IteratorClient {

    public static void main(String[] args) {
        NameRepository nameRepository = new NameRepository();
        final Iterator iterator = nameRepository.getIterator();
        while (iterator.hasNext()){
            String name = (String) iterator.next();
            System.out.println(name);
        }
    }
}
