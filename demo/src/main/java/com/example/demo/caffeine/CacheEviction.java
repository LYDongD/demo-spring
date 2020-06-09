package com.example.demo.caffeine;

import com.example.demo.cache.User;
import com.example.demo.cache.UserDao;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 缓存过期的三种策略
 * @author Liam(003046)
 * @date 2020/5/23 下午2:12
 */
@Slf4j
public class CacheEviction {

    /**
     *  模拟失效策略（LFU)
     */
    public static void sizeBase() throws Exception{
        Cache<String, User> cache = Caffeine.newBuilder()
                .maximumSize(2).build();
        cache.put("A", new UserDao().readFromDb("A"));
        cache.put("B", new UserDao().readFromDb("B"));

        //使用1w次A
//        for (int i = 0; i < 100000; i++){
//            cache.getIfPresent("A");
//        }
//
//        //使用100次B
//        for (int i = 0; i < 100; i++){
//            cache.getIfPresent("B");
//        }

        cache.put("C", new UserDao().readFromDb("C"));
        //缓存失效非实时（定时任务）
        Thread.sleep(7);
        List<String> keys = Arrays.asList("A", "B", "C");
        log.info("cache: {}",cache.getAllPresent(keys));
    }

    public static void timeBase() throws Exception{
        Cache<String, User> cache = Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.SECONDS).build();
        String key = "A";
        cache.put(key, new UserDao().readFromDb(key));
        log.info("cacheA: {}", cache.getIfPresent(key));

        Thread.sleep(5000);
        log.info("cacheA: {}", cache.getIfPresent(key));
    }

    public static void referenceBase() throws Exception{
        Cache<String, User> cache = Caffeine.newBuilder()
                .weakKeys()
                .weakValues()
                .build();
        String key = "A";
        cache.put(key, new UserDao().readFromDb(key));
        log.info("cacheA: {}", cache.getIfPresent(key));

        System.gc();
        log.info("cacheA: {}", cache.getIfPresent(key));
    }

    public static void main(String[] args) throws Exception{
        referenceBase();
    }
}
