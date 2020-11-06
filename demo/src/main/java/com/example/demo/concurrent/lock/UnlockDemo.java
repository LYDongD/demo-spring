package com.example.demo.concurrent.lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnlockDemo {

    public static void main(String args[]) {
        new ReentrantLock().unlock();
    }
}
