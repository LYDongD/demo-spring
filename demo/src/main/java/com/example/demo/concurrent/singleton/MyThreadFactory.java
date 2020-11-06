package com.example.demo.concurrent.singleton;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  create Thread with threadFactory
 */
@Slf4j
public class MyThreadFactory implements ThreadFactory {

    private final String namePrefix;

    private final AtomicInteger number = new AtomicInteger(0);

    public MyThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    @Override
    public Thread newThread(Runnable t) {
        String name = this.namePrefix + number.getAndIncrement();
        Thread thread = new Thread(t);
        thread.setName(name);
        return thread;
    }

    public static void main(String[] args) {

        MyThreadFactory myThreadFactory = new MyThreadFactory("custom-worker-");
        for (int i = 0; i < 9; i++) {
            Thread thread = myThreadFactory.newThread(() -> {
                log.info("my name is : {}", Thread.currentThread().getName());
            });
            thread.start();
        }
    }
}
