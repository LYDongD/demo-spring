package com.demo.hutool.date;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * Date相关工具类
 *
 * @author Liam(003046)
 * @date 2019/8/21 上午11:14
 */
public class DateUtilDemo {


    public static void main(String args[]) {
        String result = DateUtil.now();
        System.out.println(result);
    }
}
