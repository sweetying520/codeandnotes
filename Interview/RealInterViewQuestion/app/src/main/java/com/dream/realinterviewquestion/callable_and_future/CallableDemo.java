package com.dream.realinterviewquestion.callable_and_future;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * function: Callable：带返回值且可抛异常的 Runnable
 *
 * @author zy
 * @since 2023/3/7
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        Callable<Integer> callable = () -> {
//            System.out.println("线程执行");
//            Thread.sleep(3000);
//            return 1;
//        };
//        FutureTask<Integer> task = new FutureTask<>(callable);
//        new Thread(task).start();
//        System.out.println(task.isDone());
//        System.out.println(task.get(4000, TimeUnit.MILLISECONDS));
//        System.out.println(task.isDone());
//        System.out.println(666);

        Map<String, String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        map.put("5","5");
        map.put("6","5");
        Map<String, String> map1 = new HashMap<>(map);
        map1.put("4","4");
        for (Map.Entry<String, String> entry : map1.entrySet()) {
            System.out.println(entry.getKey() + "  " + entry.getValue());
        }
    }
}
