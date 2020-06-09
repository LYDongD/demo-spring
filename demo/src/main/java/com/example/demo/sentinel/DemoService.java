package com.example.demo.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

/**
 * @author Liam(003046)
 * @date 2019/8/12 下午5:22
 */
//@Service
public class DemoService {

    @SentinelResource(value = "demoMethod", blockHandler = "demoMethodFailCall")
    public void demoMethod(String name, Integer time) throws Exception{
        Thread.sleep(time);
        System.out.println(Thread.currentThread().getName() + ": " + time + "ms");
    }

    public void demoMethodFailCall(String name, Integer time, BlockException ex) {
        System.out.println("fail" + " " + name);
        System.out.println(ex.getRule().getResource());
    }
}
