package com.example.demo.concurrent.counter;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程安全的计数器
 * @author Liam(003046)
 * @date 2020/6/22 下午12:22
 */
@Slf4j
public class LockCounter {

    private Integer count = 0;

    public Integer get(){
        return this.count;
    }

    public synchronized void addOne(){
        this.count++;
    }

    public static void main(String[] args) throws Exception{
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++){
            lockDemo();
        }
        Long delta = System.currentTimeMillis() - start;
        log.info("elapsed: {}", delta / 1000.0);
    }

    private static void lockDemo() throws Exception{

        LockCounter counter = new LockCounter();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++){
                counter.addOne();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++){
                counter.addOne();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        log.info("result: {}", counter.get());
    }


    private static void casDemo() throws Exception{
        CASCounter casCounter = new CASCounter();
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++){
                casCounter.addOne();
            }
        });
        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 1000; i++){
                casCounter.addOne();
            }
        });

        thread3.start();
        thread4.start();
        thread3.join();
        thread4.join();
        log.info("result: {}", casCounter.get());
    }
}
