package com.dream.realinterviewquestion.callable_and_future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * function: Future：一个方法计算过程可能非常耗时，等待方法返回显然不可取。可以在执行方法时，
 *                   立马就返回一个 Future 对象，通过这个 Future 对象来控制方法的计算过程
 *
 * @author zy
 * @since 2023/3/7
 */
public class FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<String> callable = () -> {
            Thread.sleep(2000);
            return "erdai666";
        };
        final Future<String> future = executorService.submit(callable);
        System.out.println(future.get());
        System.out.println(future.isDone());
        System.out.println(future.isCancelled());
        executorService.shutdown();
    }
}
