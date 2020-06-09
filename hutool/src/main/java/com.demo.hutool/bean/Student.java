package com.demo.hutool.bean;

/**
 * @author Liam(003046)
 * @date 2020/4/23 下午3:19
 */
public class Student extends PersonWrapper.Person{

    public Student(PersonWrapper personWrapper) {
        personWrapper.super("liam");
    }
}
