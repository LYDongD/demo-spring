package com.example.demo.concurrent.liveLock;

import lombok.Data;

/**
 * 资源，只能被一个线程执行
 * @author Liam(003046)
 * @date 2020/6/17 上午10:37
 */
@Data
public class Resource {

    /**
     *  资源拥有者
     */
    private Worker owner;
}
