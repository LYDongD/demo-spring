package com.example.demo.sentinel;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 加上配置后不需要进行限流
 * @author Liam(003046)
 * @date 2019/8/13 下午1:50
 */
//@Component
public class DegradeRuleConfig {

    private static final String resource = "demoMethod";

    @PostConstruct
    public void config(){
        List<DegradeRule> degradeRules = new ArrayList<>();
        DegradeRule degradeRule = new DegradeRule();
        degradeRule.setResource(resource);
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_RT);//按RT
        degradeRule.setCount(200);
        degradeRule.setTimeWindow(2);
        degradeRules.add(degradeRule);
        DegradeRuleManager.loadRules(degradeRules);
    }
}
