package com.example.demo.concurrent.readWriteLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Thread.sleep;

/**
 * 读写锁
 * @author Liam(003046)
 * @date 2020/6/27 上午11:20
 */
@Slf4j
public class ReadWriteLockDemo {

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final Lock readLock = readWriteLock.readLock();

    private final Lock writeLock = readWriteLock.writeLock();

    private Integer count = 0;

    private Integer get(){
        return this.count;
    }

    private void addOne(){
        this.count++;
    }

    /**
     *  模拟锁升级（会导致线程永久阻塞）
     */
    public void lockUpgrade(){
        //读共享变量
        readLock.lock();
        try {
            log.info("current count: {}", this.get());
            sleep(3000);
            writeLock.lock();
            try {
                this.addOne();
            }finally {
                writeLock.unlock();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++){
            executorService.submit(() -> {
                ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();
                readWriteLockDemo.lockUpgrade();
            });
        }
    }
}
