package com.example.demo.caffeine;

import com.example.demo.cache.User;
import com.example.demo.cache.UserDao;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 异步刷新
 * @author Liam(003046)
 * @date 2020/5/23 下午3:14
 */
@Slf4j
public class CacheRefresh {

    public static void refresh() throws Exception{
        LoadingCache<String, User> cache = Caffeine.newBuilder()
                .maximumSize(10_000)
                .refreshAfterWrite(3, TimeUnit.SECONDS)
                .build(key -> new UserDao().readFromDbDynamic(key));
        log.info("cache: {}", cache.get("A"));
        Thread.sleep(5000);
        log.info("cache: {}", cache.get("A")); //依然是旧值，实际上访问的时候才触发刷新，下次将拿到新的值
        log.info("cache: {}", cache.get("A"));
    }

    public static void main(String[] args) throws Exception{
        refresh();
    }
}
