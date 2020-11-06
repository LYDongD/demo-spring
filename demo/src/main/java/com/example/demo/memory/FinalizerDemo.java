package com.example.demo.memory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.PriorityQueue;

/**
 * 实现finalize方法
 * @author Liam(003046)
 * @date 2020/6/15 下午1:42
 */
public class FinalizerDemo {

    public void finalize() {
        System.out.println("finalize...");
    }

    public static void main(String[] args) throws Exception{
//        FinalizerDemo finalizerDemo = new FinalizerDemo();
//        finalizerDemo = null;
//        System.gc();

//        String jstr = "{\"routerVendor\": \"routerVendor\",\"cpuSerial\": 0,\"routerMac\": \"52:54:00:12:35:02\",\"iccid\":898602B8191651455567,\"mtu\": \"电信\",\"mtuCompany\": \"中国电信股份有限公司南京分公司\",\"soBuildId\": \"KTU84P\",\"screenWidth\": 1920,\"screenHeight\": 1920,\"targetTemplateVersion\": \"value\",\"printerSize\": 1}";
//        String jstt = "{\"routerVendor\": \"routerVendor\",\"cpuSerial\": 0,\"routerMac\": \"52:54:00:12:35:02\",\"iccid\":89014103211118510002,\"mtu\": \"电信\",\"mtuCompany\": \"中国电信股份有限公司南京分公司\",\"soBuildId\": \"KTU84P\",\"screenWidth\": 1920,\"screenHeight\": 1920,\"targetTemplateVersion\": \"value\",\"printerSize\": 0}";
//        JSONObject jsonObject = JSON.parseObject(jstr);


        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(3, (o1, o2) -> o2.compareTo(o1));
        maxHeap.add(1);
        maxHeap.add(3);
        maxHeap.add(5);
        maxHeap.add(7);
        for (Integer ele : maxHeap) {
            System.out.print(ele);
            System.out.println(" --> ");
        }
    }
}
