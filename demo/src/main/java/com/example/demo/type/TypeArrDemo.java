package com.example.demo.type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**d
 * @author Liam(003046)
 * @date 2020/2/15 下午8:59
 */
public class TypeArrDemo {


    /**
     * finally throw an ArrayStoreException at runtime
     */
    public static void f() {
        Object[] b = new String[2];
        b[0] = "test";
        b[1] = Integer.valueOf(1);
    }


    public static void f2() {
        Number number = new Integer(1);

        List<Number> numbers = new ArrayList<>();
        numbers.add(new Integer(2));

//        ArrayList<Number> numberArrayList = new ArrayList<Integer>();
        List<? extends Number> list = new ArrayList<Integer>();
//        list.add(new Integer(1));

        Collection<Object> objectCollection = new ArrayList<>();
        objectCollection.add(Integer.valueOf(1));
//        List<Object> a  = new ArrayList<String>();
        List<? extends Object> a = new ArrayList<String>();
    }

    public static void main(String[] args) {
        f();
    }

}
