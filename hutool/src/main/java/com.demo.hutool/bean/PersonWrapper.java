package com.demo.hutool.bean;

/**
 * @author Liam(003046)
 * @date 2020/4/23 下午3:20
 */
public class PersonWrapper {

    public class Person {

        public Person(String name){
            System.out.println("hello: " + name);
        }
    }

    public static class Teacher {

    }

    public class Student extends PersonWrapper.Person {

        public Student(PersonWrapper personWrapper) {
            personWrapper.super("liam");
        }
    }
}
