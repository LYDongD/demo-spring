package com.example.demo.lifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Liam(003046)
 * @date 2020/1/19 上午11:25
 */
//@Component
public class DemoBean implements InitializingBean, BeanFactoryAware, ApplicationContextAware, DisposableBean {


    @PostConstruct
    public void construct() {
        System.out.println("DemoBean @PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("DemoBean PreDestroy");
    }

    public void destroy() {
        System.out.println("DemoBean destory");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("DemoBean afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("DemoBean ApplicationAware");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("DemoBean beanFactoryAware");
    }


}
