package com.example.demo.concurrent.semaphore;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.function.Consumer;

import static java.lang.Thread.sleep;

/**
 * 对象池
 *
 * @author Liam(003046)
 * @date 2020/6/26 下午3:40
 */
@Slf4j
public class ObjectPool<T> {

    /**
     * 信号量，控制获取资源的线程数
     */
    private final Semaphore counter;

    private final List<T> objects = new CopyOnWriteArrayList<>();

    public ObjectPool(Integer size, Class<T> clazz) throws Exception {
        this.counter = new Semaphore(size);
        for (int i = 0; i < size; i++) {
            objects.add((clazz.newInstance()));
        }
    }

    /**
     * 提交任务
     */
    public void submit(Consumer<T> consumer) {
        //获取信号，无可用信号则阻塞
        try {
            this.counter.acquire();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            return;
        }
        T object = null;
        try {
            //获取对象并执行
            object = objects.remove(0);
            consumer.accept(object);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //归还对象并释放信号量
            this.objects.add(object);
            this.counter.release();
        }
    }


    public static void main(String[] args) throws Exception {
        ObjectPool<String> objectPool = new ObjectPool<>(10, String.class);
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                objectPool.submit(s -> {
                    log.info("thread: {}", Thread.currentThread().getName());
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            });
            thread.start();
        }
    }
}
