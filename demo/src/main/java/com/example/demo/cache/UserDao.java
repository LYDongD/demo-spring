package com.example.demo.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Liam(003046)
 * @date 2020/5/19 下午1:58
 */
@Component
@Slf4j
public class UserDao {

    private static final Map<String, User> mockUsers = new HashMap<>();

    public User readFromDb(String name) {
        return new User(name);
    }

    public User readFromDbDynamic(String name) {
        return new User(name + System.currentTimeMillis());
    }

    public User readFromDbBlock(String name, Integer seconds) throws Exception{
//        TraceUtil.printStackTraceClass();
        Thread.sleep(1000 * seconds);
        return new User(name);
    }


    @Cacheable(value = "user")
    public User getUserByName(String name) throws Exception{
        log.info("read from db: {}", name);
        return new User(name);
    }

    @CacheEvict(value = "user")
    public void updateByUserName(String name) throws Exception{
        log.info("user: " + name + " has benn updated");
    }
}
