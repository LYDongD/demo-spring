package com.example.demo.sentinel;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.fcbox.sentinel.SentinelNacosAutoReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @author Liam(003046)
 * @date 2019/8/13 下午1:59
 */
//@Configuration
//@PropertySources({@PropertySource(value="classpath:/config/nacos.properties")})
//@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "${nacos.config.server-addr}", namespace = "${nacos.config.namespace}"))
public class NacoConfig {
    @Bean
    public SentinelNacosAutoReader sentinelNacosAutoReader() {
        return new SentinelNacosAutoReader();
    }
}
