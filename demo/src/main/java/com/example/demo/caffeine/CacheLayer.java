package com.example.demo.caffeine;

import com.example.demo.cache.TraceUtil;
import com.example.demo.cache.User;
import com.example.demo.cache.UserDao;
import com.github.benmanes.caffeine.cache.CacheWriter;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.RemovalCause;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * 多级缓存
 *
 * @author Liam(003046)
 * @date 2020/5/23 下午7:33
 */
@Slf4j
public class CacheLayer {

    /**
     * 实现多级缓存
     * 策略：1 write through 2 write back
     */
    public static void layerCache() throws Exception{
        LoadingCache<String, User> cache = Caffeine.newBuilder()
                .writer(new CacheWriter<String, User>() {
                    @Override
                    public void write(@NonNull String key, @NonNull User value) {
                        //写入redis
                        log.info("write to redis: {} - {}", key, value);
                    }
                    @Override
                    public void delete(@NonNull String key, @Nullable User value, @NonNull RemovalCause cause) {
                        //删除redis
                        log.info("delete redis: {} - {}", key, value);
                    }
                }).build(key -> new UserDao().readFromDb(key));

        String name = "A";
        cache.put(name, new UserDao().readFromDb(name));
        cache.invalidate(name);
        Thread.sleep(5000);
    }

    public static void main(String args[]) throws Exception{
        layerCache();
    }
}
