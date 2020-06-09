package com.example.demo;

import com.example.demo.cache.User;
import com.example.demo.cache.UserDao;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;

@SpringBootApplication
@Slf4j
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Autowired
    UserDao userDao;

    @Autowired
    CacheManager cacheManager;

    @Override
    public void run(String... args) throws Exception {

        User user = userDao.getUserByName("A");
        log.info("user :{}", user);

        user = userDao.getUserByName("A");
        log.info("user :{}", user);

        userDao.updateByUserName("A");
        user = userDao.getUserByName("A");
        log.info("user :{}", user);

        Cache<String, User> cache = (Cache<String, User>) cacheManager.getCache("user").getNativeCache();
        log.info("统计指标:{}",cache.stats().hitRate());
    }
}
