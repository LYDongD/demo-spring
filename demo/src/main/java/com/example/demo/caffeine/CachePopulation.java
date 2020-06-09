package com.example.demo.caffeine;

import com.example.demo.cache.User;
import com.example.demo.cache.UserDao;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 缓存读写的四种方式
 *
 * @author Liam(003046)
 * @date 2020/5/23 上午11:40
 */
@Slf4j
public class CachePopulation {

    public static void manualCache() {
        Cache<String, User> cache = Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();


        //read
        String nameA = "A";
        User userA = cache.getIfPresent(nameA);
        log.info("userA: {}", userA);

//        //read or write
//        userA = cache.get(nameA, key -> new UserDao().readFromDb(key));
//        log.info("userA: {}", userA);

        //write
//        String nameB = "B";
//        cache.put(nameB, new User(nameB));
//        User userB = cache.getIfPresent(nameB);
//        log.info("userB: {}", userB);
//
//        //invalidate
//        cache.invalidate(nameB);
//        userB = cache.getIfPresent(nameB);
//        log.info("userB: {}", userB);
    }

    public static void loadingCache() {
        LoadingCache<String, User> cache = Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
//                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(key -> new UserDao().readFromDb(key)); //auto loading key-value

        //自动填充
        String nameA = "A";
        User userA = cache.get(nameA);
        log.info("userA: {}", userA);

//        String nameB = "B";
//        List<String> keys = Arrays.asList(nameA, nameB);
//        Map<String, User> cacheAB = cache.getAll(keys);
//        log.info("userAB: {}", cacheAB);
    }

    public static void asyncLoadingCache() throws Exception {
        AsyncLoadingCache<String, User> cache = Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
//                .executor(Executors.newFixedThreadPool(2))
                .buildAsync((key) -> new UserDao().readFromDbBlock(key, 5));

        String nameA = "A";
        CompletableFuture<User> userA = cache.get(nameA);
        log.info("获取结果...");
        //如果异步任务未结束，将阻塞
        log.info("userA: {}", userA.get());
    }


    public static void main(String[] args) throws Exception {
//        manualCache();
//        loadingCache();
        asyncLoadingCache();
    }
}
