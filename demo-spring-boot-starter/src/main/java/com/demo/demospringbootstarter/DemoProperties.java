package com.demo.demospringbootstarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置信息实体，从属性文件加载
 * @author Liam(003046)
 * @date 2019/8/10 上午11:40
 */
@ConfigurationProperties(prefix = "demo")
public class DemoProperties {

    private String sayWhat;
    private String toWho;


    public String getSayWhat() {
        return sayWhat;
    }

    public void setSayWhat(String sayWhat) {
        this.sayWhat = sayWhat;
    }

    public String getToWho() {
        return toWho;
    }

    public void setToWho(String toWho) {
        this.toWho = toWho;
    }
}
