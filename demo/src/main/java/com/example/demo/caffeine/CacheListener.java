package com.example.demo.caffeine;

import com.example.demo.cache.User;
import com.example.demo.cache.UserDao;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * 缓存监听器
 *
 * @author Liam(003046)
 * @date 2020/5/23 下午7:11
 */
@Slf4j
public class CacheListener {

    public static void invalidNotification() throws Exception {

        //listener execute async use forkJoinPool async
        Cache<String, User> cache = Caffeine.newBuilder()
                .maximumSize(1)
                .removalListener((key, value, cause) -> {
                    try {
                        log.info("key:{} has been removed: {}", key, cause);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                })
                .build();
        String keyA = "A";
        String keyB = "B";
        cache.put(keyA, new UserDao().readFromDb(keyA));
        cache.put(keyB, new UserDao().readFromDb(keyB));
//        cache.invalidate(keyA);
        Thread.sleep(1000);
        log.info("cache: {}", cache.getIfPresent("A"));
    }

    public static void main(String args[]) throws Exception{
        invalidNotification();
    }
}
