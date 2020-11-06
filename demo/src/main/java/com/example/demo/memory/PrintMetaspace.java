package com.example.demo.memory;

/**
 * @author Liam(003046)
 * @date 2020/6/15 下午1:53
 */
public class PrintMetaspace {

    private final static Object lock = new Object();

    public static void main(String[] args) throws Exception{
        String a = "hello world";
        synchronized (lock){
            lock.wait();
        }
    }
}
