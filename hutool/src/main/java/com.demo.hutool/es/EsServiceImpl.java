package com.demo.hutool.es;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * es数据的读写服务（单例）
 * 单例，持有ESClient
 *
 */
@Slf4j
public class EsServiceImpl implements EsService {

    private static final EsServiceImpl instance = new EsServiceImpl();

    public static EsServiceImpl getInstance() {
        return instance;
    }

    private static final String INDEX = "hospitals";

    private EsServiceImpl() {
    }


    private static TransportClient client;

    static {
        try {
            Settings settings = Settings.builder()
                    .put("cluster.name", "elasticsearch").build();
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(
                            new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
            log.info("ES client连接成功: {}", client.toString());
        } catch (Exception e) {
            log.error("ES client 初始化失败");
        }
    }


    @Override
    public void save(List<Hospital> hospitals) {
        for (Hospital hospital : hospitals) {
            String jsonStr = JSON.toJSONString(hospital);
            Map<String, Object> dataMap = JSON.parseObject(jsonStr, new TypeReference<Map<String, Object>>() {
            });
            client.prepareIndex(INDEX, null).setSource(dataMap).get();
        }
    }

    @Override
    public List<Hospital> searchText(String keyword, int pageNo, int pageSize) {

        //构建搜索参数(多字段搜索）
        SearchSourceBuilder sourceBuilder = SearchSourceBuilder.searchSource();
        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(keyword, "address", "name", "phone")
                .type(MultiMatchQueryBuilder.Type.CROSS_FIELDS);
        sourceBuilder.query(queryBuilder);

        //执行搜索
        SearchResponse searchResponse = client.prepareSearch(INDEX)
                .setSource(sourceBuilder)
                .setFrom(pageNo)
                .setSize(pageSize)
                .execute()
                .actionGet();

        //解析结果
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<Hospital> hospitalList = new ArrayList<>();
        if (searchHists.length > 0) {
            for (SearchHit hit : searchHists) {
                //以json字符串的方式读取
                String hitStr = hit.getSourceAsString();
                Hospital hospital = JSON.parseObject(hitStr, Hospital.class);
                hospital.setScore(hit.getScore());
                hospitalList.add(hospital);
            }
        }
        return hospitalList;
    }

    @Override
    public List<Hospital> searchTerm(Integer level) {

        //构造参数
        SearchSourceBuilder sourceBuilder = SearchSourceBuilder.searchSource();
        QueryBuilder queryBuilder = QueryBuilders.termQuery("level", level);
        //取消相关性评分（score=1.0)
        QueryBuilder constantScoreQuery = new ConstantScoreQueryBuilder(queryBuilder);
        sourceBuilder.query(constantScoreQuery);

        //执行搜索
        SearchResponse searchResponse = client.prepareSearch(INDEX)
                .setSource(sourceBuilder)
                .execute()
                .actionGet();

        //解析结果
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<Hospital> hospitalList = new ArrayList<>();
        if (searchHists.length > 0) {
            for (SearchHit hit : searchHists) {
                //以json字符串的方式读取
                String hitStr = hit.getSourceAsString();
                Hospital hospital = JSON.parseObject(hitStr, Hospital.class);
                hospital.setScore(hit.getScore());
                hospitalList.add(hospital);
            }
        }
        return hospitalList;
    }


    @Override
    public List<Hospital> searchNearby(double longitude, double latitude, Integer distance) {
        //构建搜索和排序参数
        SearchSourceBuilder sourceBuilder = SearchSourceBuilder.searchSource();
        QueryBuilder queryBuilder = new GeoDistanceQueryBuilder("point")
                .point(latitude, longitude)
                .distance(distance, DistanceUnit.METERS)
                .geoDistance(GeoDistance.ARC);

        //默认按距离排序
        GeoDistanceSortBuilder sortBuilder = new GeoDistanceSortBuilder("point", latitude, longitude)
                .unit(DistanceUnit.METERS)
                .order(SortOrder.ASC);
        sourceBuilder.query(queryBuilder).sort(sortBuilder);

        //执行搜索
        SearchResponse searchResponse = client.prepareSearch(INDEX)
                .setSource(sourceBuilder)
                .execute()
                .actionGet();

        //解析结果，生成距离
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<Hospital> hospitalList = new ArrayList<>();
        if (searchHists.length > 0) {
            for (SearchHit hit : searchHists) {
                //以json字符串的方式读取
                String hitStr = hit.getSourceAsString();
                Hospital hospital = JSON.parseObject(hitStr, Hospital.class);

                //取出距离
                Object[] distantHits = hit.getSortValues();
                hospital.setDistance((Double)distantHits[distantHits.length - 1]);
                hospital.setScore(hit.getScore());
                hospitalList.add(hospital);
            }
        }
        return hospitalList;
    }
}
