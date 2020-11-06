package com.example.demo.concurrent.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 *  double check synchronize singleton
 */
@Slf4j
public class SingletonDemo {

    private static volatile SingletonDemo instance;

    private SingletonDemo(){};

    public static SingletonDemo getInstance() throws Exception{
        if (instance == null) {
            Thread.sleep(1000);
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    log.info("create singleton instance in thread : {}", Thread.currentThread().getName());
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public void print(){
        log.info("hello: {}", Thread.currentThread().getName());
    }


    public static void main(String[] args) {
        //模拟10个线程同时获取单例，要求只有一个线程完成创建
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    getInstance().print();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            thread.start();
        }
    }
}
