package com.demo.demospringbootstarter;

import org.springframework.stereotype.Service;

/**
 * @author Liam(003046)
 * @date 2019/8/10 上午11:42
 */
@Service
public class DemoService {

    private String sayWhat;
    private String toWho;

    public DemoService(String  sayWhat, String toWho) {
        this.sayWhat = sayWhat;
        this.toWho = toWho;
    }

    public String say() {
        return this.sayWhat + "! " + this.toWho;
    }
}
