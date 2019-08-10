package com.example.demo;

import com.demo.demospringbootstarter.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Liam(003046)
 * @date 2019/8/10 上午11:55
 */
@RestController
public class DemoController {

    @Resource
    private DemoService demoService;

    @GetMapping("/say")
    public String sayWhat() {
        return demoService.say();
    }
}
