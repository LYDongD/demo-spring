package com.example.demo.lifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Liam(003046)
 * @date 2020/1/19 上午11:32
 */
//@Component
public class Demo2Bean implements InitializingBean, BeanPostProcessor, BeanFactoryPostProcessor {

    @Autowired
    Demo3Bean demo3Bean;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("DemoBean postProcessBeanFactory");
        System.out.println(demo3Bean == null);
    }

    @PostConstruct
    public void construct(){
        System.out.println("Demo2Bean @PostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Demo2Bean afterPropertiesSet");
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Demo2Bean postProcessBeforeInitialization: " + beanName);
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Demo2Bean postProcessAfterInitialization: " + beanName);
        return bean;
    }

//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        System.out.println("Demo2Bean ApplicationAware");
//    }
//
//    @Override
//    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//        System.out.println("Demo2Bean beanFactoryAware");
//    }
}
