package com.example.demo.sentinel;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Liam(003046)
 * @date 2019/8/12 下午5:07
 */
public class SentinelDemo {

    private static final String resource = "test";

    public static void main(String args[]) throws Exception{
        initDegradeRules();
        DemoService demoService = new DemoService();
        Random random = new Random();
        while (true){
            Integer time = random.nextInt(300);
            demoService.demoMethod("hello world", time);
        }
    }


    private static void initDegradeRules(){
        List<DegradeRule> degradeRules = new ArrayList<>();
        DegradeRule degradeRule = new DegradeRule();
        degradeRule.setResource(resource);
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_RT);//按RT
        degradeRule.setTimeWindow(100);
        degradeRules.add(degradeRule);
        DegradeRuleManager.loadRules(degradeRules);
    }
}
