package com.example.demo.concurrent.completionService;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Title: CompletionServiceDemo.java
 * @Package com.example.demo.concurrent.completionService
 * @Description: 获取批量异步任务执行结果
 * @Author: lee
 * @Date: 2020/7/5 下午2:39
 */
@Slf4j
public class CompletionServiceDemo {

    public static void main(String args[]) throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletionService<Long> completionService = new ExecutorCompletionService<>(executorService);
        completionService.submit(() -> {
            Thread.sleep(5000);
            return System.currentTimeMillis();
        });

        completionService.submit(() -> {
            Thread.sleep(3000);
            return System.currentTimeMillis();
        });

        completionService.submit(() -> {
            Thread.sleep(1000);
            return System.currentTimeMillis();
        });

        for (int i = 0; i < 3; i++){
            Long time = completionService.take().get();
            log.info("time: {}", time);
        }
    }
}
