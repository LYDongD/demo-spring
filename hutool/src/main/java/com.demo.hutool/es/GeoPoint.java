package com.demo.hutool.es;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Data;

/**
 * 地理位置点
 */
@Data
public class GeoPoint {

    /**
     *  纬度
     */
    private double lat;

    /**
     *  经度
     */
    private double lon;

    public GeoPoint(double lon, double lat) {
        this.lat = lat;
        this.lon = lon;
    }
}
