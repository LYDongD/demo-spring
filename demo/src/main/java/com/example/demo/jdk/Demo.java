package com.example.demo.jdk;

/**
 * @author Liam(003046)
 * @date 2019/9/18 上午11:54
 */
public class Demo {

    public static void main(String args[]) {
        Child child = new Child();
//        child.setIsFault(1);
        child.setFault(1);
        System.out.println(child.getIsFault());
        System.out.println(child.getFault());
    }
}
