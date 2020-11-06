package com.example.demo.concurrent.liveLock;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 资源执行者
 * @author Liam(003046)
 * @date 2020/6/17 上午10:37
 */
@Data
@Slf4j
public class Worker {

    /**
     *  是否活跃，只有活跃的worker才能执行资源
     */
    private boolean active;

    public Worker(boolean active) {
        this.active = active;
    }

    /**
     *  执行资源
     *  优先谦让，如果其他线程活跃，先让其他线程执行
     */
    public void work(Resource resource, Worker other) {

        //只有活跃且拥有资源执行权才执行
        while (active){

            //没有资源执行权
            if (resource.getOwner() != this) {
                //等待并重试
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            //优先让其他线程执行
            if (other.isActive()) {
                log.info("yet to other thread");
                resource.setOwner(other);
                continue;
            }

            //执行
            log.info("process the resource");

            //执行完后让出资源给其他线程
            active = false;
            resource.setOwner(other);
        }
    }

}
