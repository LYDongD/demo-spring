package com.example.demo.concurrent.liveLock;

/**
 * @author Liam(003046)
 * @date 2020/6/17 上午10:39
 */
public class LiveLockDemo {

    public static void main(String args[]) {

        Worker workerA = new Worker(true);
        Worker workerB = new Worker(true);
        Resource resource = new Resource();
        resource.setOwner(workerA);

        Thread threadA = new Thread(() -> {
            workerA.work(resource, workerB);
        });

        Thread threadB = new Thread(() -> {
            workerB.work(resource, workerA);
        });

        threadA.start();
        threadB.start();
    }
}
