package com.example.demo.concurrent.liveLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * 死锁: 用java.util.lock实现
 * @author Liam(003046)
 * @date 2020/6/16 上午11:21
 */
public class DeadLockDemo2 {

    private final Lock lockA = new ReentrantLock();
    private final Lock lockB = new ReentrantLock();
    private Integer resourceA = 0;
    private Integer resourceB = 0;

    /**
     *  多线程循环等待资源
     *  1 多个线程访问多个有相互依赖的资源
     *  2 访问顺序不一致
     */
    public void deadLock() throws Exception{

        Thread threadA = new Thread(() -> {
            lockA.lock();
            try {
                resourceA++;
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lockB.lock();
                try {
                    resourceB++;
                }finally {
                    lockB.unlock();
                }
            }finally {
                lockA.unlock();
            }

        });

        Thread threadB = new Thread(() -> {
            lockB.lock();
            try {
                resourceB--;
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lockA.lock();
                try {
                    resourceA--;
                }finally {
                    lockA.unlock();
                }
            }finally {
                lockB.unlock();
            }
        });

        threadA.start();
        threadB.start();
    }

    public static void main(String[] args) throws Exception{
        DeadLockDemo2 deadLockDemo = new DeadLockDemo2();
        deadLockDemo.deadLock();
    }

}
