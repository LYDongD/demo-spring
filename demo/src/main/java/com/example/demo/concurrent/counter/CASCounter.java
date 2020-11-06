package com.example.demo.concurrent.counter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Liam(003046)
 * @date 2020/6/22 下午12:32
 */
public class CASCounter {

    private AtomicInteger count = new AtomicInteger();

    public Integer get(){
        return this.count.get();
    }

    public void addOne(){
        this.count.addAndGet(1);
    }
}
