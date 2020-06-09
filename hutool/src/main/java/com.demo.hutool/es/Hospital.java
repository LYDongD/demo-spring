package com.demo.hutool.es;

import lombok.Data;

/**
 * 医院
 */
@Data
public class Hospital {

    /**
     *  空间坐标点
     */
    GeoPoint point;

    /**
     *  医院名称
     */
    String name;

    /**
     *  详细地址
     */
    String address;

    /**
     *  电话
     */
    String phone;

    /**
     *  等级：1，2，3
     */
    Integer level;

    /**
     *  距离（米）
     */
    Double distance;

    /**
     *  文档得分
     */
    Float score;
}
