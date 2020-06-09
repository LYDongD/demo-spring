package com.example.demo.caffeine;

import com.example.demo.cache.User;
import com.example.demo.cache.UserDao;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import lombok.extern.slf4j.Slf4j;

/**
 * 缓存统计
 * @author Liam(003046)
 * @date 2020/5/23 下午9:58
 */
@Slf4j
public class CacheStatistic {

    public static void recordStateCache() throws Exception{
        LoadingCache<String, User> cache = Caffeine.newBuilder()
                .maximumSize(2)
                .recordStats()
                .build(key -> new UserDao().readFromDbBlock(key, 1));
        cache.put("A", new UserDao().readFromDb("A"));
        cache.get("A");
        cache.get("B");
        cache.get("C");

        Thread.sleep(1000);

        CacheStats cacheStats = cache.stats();
        log.info("hit rate: {}", cacheStats.hitRate());
        log.info("evictCount: {}", cacheStats.evictionCount());
        log.info("averageLoadPenalty: {}", cacheStats.averageLoadPenalty());

    }

    public static void main(String[] args) throws Exception{
        recordStateCache();
    }



}
