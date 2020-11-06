package com.example.demo.concurrent.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * Lock使用案例
 *
 * @author Liam(003046)
 * @date 2020/6/24 下午5:54
 */
@Slf4j
public class LockDemo {

    private Lock lock = new ReentrantLock();

    private Integer count = 0;


    public void lockInterruptDemo() throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            lock.lock();
            try {
                this.count++;
                sleep(600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            synchronized (this) {
                this.count++;
                try {
                    sleep(600000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {

            //等待的时候可以响应中断
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            try {
                this.count++;
                sleep(600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }


//            synchronized (this) {
//                this.count++;
//                try {
//                    sleep(600000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }


        });

        thread1.start();
        thread2.start();
        sleep(2000);
        log.info("thread2 status: {}", thread2.getState());

        thread2.interrupt();
    }


    public void lockTimeoutDemo() throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            lock.lock();
            try {
                this.count++;
                sleep(600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread thread2 = new Thread(() -> {

            //等待的时候可以设置超时, 且支持中断
            boolean result = false;
            try {
                result = lock.tryLock(10, TimeUnit.SECONDS);
                if (!result){
                    log.info("获取锁超时");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            try {
                this.count++;
                sleep(600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        thread1.start();
        thread2.start();
        sleep(2000);
        log.info("thread2 status: {}", thread2.getState());
    }

    public void lockNoneBlock() throws InterruptedException{

        Thread thread1 = new Thread(() -> {
            lock.lock();
            try {
                this.count++;
                sleep(600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread thread2 = new Thread(() -> {

            //等待的时候可以设置超时, 且支持中断
            boolean result = lock.tryLock();
            if (!result){
                log.info("获取锁失败");
                return;
            }
            try {
                this.count++;
                sleep(600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        thread1.start();
        thread2.start();
        sleep(2000);
        log.info("thread2 status: {}", thread2.getState());
    }



    public static void main(String[] args) throws Exception {
        LockDemo lockDemo = new LockDemo();
        lockDemo.lockNoneBlock();
    }
}
