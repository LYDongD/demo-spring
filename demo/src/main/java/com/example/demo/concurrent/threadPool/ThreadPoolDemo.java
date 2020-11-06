package com.example.demo.concurrent.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title: ThreadPoolDemo.java
 * @Package com.example.demo.concurrent.threadPool
 * @Description: 线程池demo
 * @Author: lee
 * @Date: 2020/7/2 下午11:00
 */
@Slf4j
public class ThreadPoolDemo {

    public static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2, 4,
            60, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(2),
            new DefaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public static final ExecutorService executor2 = Executors.newCachedThreadPool();

    private static class DefaultThreadFactory implements ThreadFactory{

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "test-thread-" + count.getAndIncrement());
        }
    }

    public static void main(String args[]) throws Exception{
        for (int i = 0; i < 10; i++) {
            executor2.submit(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info(Thread.currentThread().getName());
            });
        }
    }
}
