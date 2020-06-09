package com.demo.hutool.es;

import java.util.List;

/**
 * elasticsearch读写服务
 */
public interface EsService {

    /**
     *  批量写入文档
     */
    public void save(List<Hospital> hospitals);


    /**
     * 全文搜索(支持分页）
     * @param keyword 关键词
     * @param pageNo 页码
     * @param pageSize 页长度
     *
     */
    public List<Hospital> searchText(String keyword, int pageNo, int pageSize);

    /**
     * 词项搜索(类似于mysql的模糊查询)
     * @param level 医院等级
     */
    public List<Hospital> searchTerm(Integer level);

    /**
     * 搜索附近的柜机(根据距离由近到远排序并返回距离)
     * @param longitude 经度
     * @param latitude 维度
     * @param distance 距离，单位m
     */
    public List<Hospital> searchNearby(double longitude, double latitude, Integer distance);
}
