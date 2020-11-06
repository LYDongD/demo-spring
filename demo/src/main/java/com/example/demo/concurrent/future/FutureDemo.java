package com.example.demo.concurrent.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @Title: FutureDemo.java
 * @Package com.example.demo.concurrent.future
 * @Description: Future demo
 * @Author: lee
 * @Date: 2020/7/3 下午4:47
 * @Version V1.0
 */
@Slf4j
public class FutureDemo {


    public static void main(String args[]) throws Exception {
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        Future<Long> future = executorService.submit(System::currentTimeMillis);
//        Long result = future.get();

        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            Thread.sleep(3000);
            return 1+2;
        });
        new Thread(futureTask).start();
        Integer result = futureTask.get();

        log.info("result : {}", result);
    }
}
