package com.example.demo.lifeCycle;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Liam(003046)
 * @date 2020/1/19 上午11:41
 */
//@Component
public class Demo3Bean {

    @PostConstruct
    public void construct(){
        System.out.println("Demo3Bean @PostConstruct");
    }
}
