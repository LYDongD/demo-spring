package com.example.demo.concurrent;

import static java.lang.Thread.sleep;

/**
 * 死锁
 * @author Liam(003046)
 * @date 2020/6/16 上午11:21
 */
public class DeadLockDemo {

    private final Object lockA = new Object();
    private final Object lockB = new Object();
    private Integer resourceA = 0;
    private Integer resourceB = 0;

    /**
     *  多线程循环等待资源
     *  1 多个线程访问多个有相互依赖的资源
     *  2 访问顺序不一致
     */
    public void deadLock() throws Exception{

        Thread threadA = new Thread(() -> {
            synchronized (lockA){
                resourceA++;

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lockB) {
                    resourceB++;
                }
            };
        });

        Thread threadB = new Thread(() -> {
            synchronized (lockB){
                resourceB--;

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lockA) {
                    resourceA--;
                }
            }
        });

        threadA.start();
        threadB.start();
    }

    public static void main(String[] args) throws Exception{
        DeadLockDemo deadLockDemo = new DeadLockDemo();
        deadLockDemo.deadLock();
    }

}
