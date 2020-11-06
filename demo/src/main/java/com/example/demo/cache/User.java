package com.example.demo.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author Liam(003046)
 * @date 2020/5/19 下午1:58
 */
@Data
@AllArgsConstructor
public class User {

    private String name;

    @Data
    private static class DateObject {
        public Object date;
    }

    public static void main(String args[]) throws Exception{

        DateObject dateObject = new DateObject();
        dateObject.setDate(new Date(86460000L));
        DateObject dateObject1 = JSON.parseObject(JSON.toJSONString(dateObject), DateObject.class);
        System.out.println(dateObject1.getDate() instanceof Long);
        System.out.println(dateObject1.getDate() instanceof Integer);


        DateObject dateObject2 = new DateObject();
        dateObject2.setDate(new Date(1594951898285L));
        DateObject dateObject3 = JSON.parseObject(JSON.toJSONString(dateObject2), DateObject.class);
        System.out.println(dateObject3.getDate() instanceof Long);
        System.out.println(dateObject3.getDate() instanceof Integer);
    }

}
