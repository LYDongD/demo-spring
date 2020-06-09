package com.example.demo.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * caffeine cache config
 * @author Liam(003046)
 * @date 2020/5/19 下午1:43
 */
@Configuration
@EnableCaching //开启注解驱动的缓存管理 -> 扫描所有添加了cache注解的方法并通过切面增强注解国能
public class CaffeineConfig {

    private static final int M = 1024 * 1024;

    public enum CacheEnum {

        USER_CACHE("user", 7 * 24 * 3600L, 10000L),
        ORDER_CACHE("order", 10 * 60L, 1000L);

        private final String cacheName;

        private final Long expireSeconds;

        private final Long maxSize;

        CacheEnum(String cacheName, Long expireSeconds, Long maxSize) {
            this.cacheName = cacheName;
            this.expireSeconds = expireSeconds;
            this.maxSize = maxSize;
        }
    }

    @Bean
    public CacheManager cacheManager(){
        SimpleCacheManager caffeineCacheManager = new SimpleCacheManager();
        List<CaffeineCache> caffeineCaches = new ArrayList<>();
        for (CacheEnum value : CacheEnum.values()) {
            Cache<Object, Object> cache = Caffeine.newBuilder()
                    .recordStats()
                    .maximumSize(value.maxSize)
                    .expireAfterWrite(value.expireSeconds, TimeUnit.SECONDS)
                    .build();

            //trans2 spring cache
            CaffeineCache caffeineCache = new CaffeineCache(value.cacheName, cache);
            caffeineCaches.add(caffeineCache);
        }
        caffeineCacheManager.setCaches(caffeineCaches);
        return caffeineCacheManager;
    }

}
