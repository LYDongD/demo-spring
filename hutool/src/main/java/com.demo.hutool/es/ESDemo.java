package com.demo.hutool.es;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liam(003046)
 * @date 2020/4/6 下午12:40
 */
@Slf4j
public class ESDemo {

    public static void main(String args[]) throws Exception{

//        EsService esService = EsServiceImpl.getInstance();

//        saveData();

//        List<Hospital> hospitals = EsServiceImpl.getInstance().searchTerm(2);
//        log.info("词项搜索：{} ", JSON.toJSONString(hospitals, SerializerFeature.PrettyFormat));

//        List<Hospital> hospitals = EsServiceImpl.getInstance().searchText("北大", 0, 10);
//        log.info("全文搜索：{} ", JSON.toJSONString(hospitals, SerializerFeature.PrettyFormat));


        List<Hospital> hospitals2 = EsServiceImpl.getInstance().searchNearby(114.133573, 22.544129, 10000);
        log.info("地理搜索：{} ", JSON.toJSONString(hospitals2, SerializerFeature.PrettyFormat));

        while (true){}
    }

    public static void saveData() throws Exception{
        //从csv读取数据
        String demoDataPath = "/Users/lee/elasticsearch-7.6.1/demo.csv";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(demoDataPath));
        String line = null;
        List<Hospital> hospitals = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null){
            Hospital hospital = new Hospital();
            String[] infos = line.split(",");
            if (infos.length >= 6) {
                hospital.setName(infos[0]);
                hospital.setAddress(infos[1]);
                hospital.setPhone(infos[2]);
                hospital.setPoint(new GeoPoint(Double.parseDouble(infos[3]), Double.parseDouble(infos[4])));
                hospital.setLevel(Integer.parseInt(infos[5]));
                hospitals.add(hospital);
            }
        }

        //写入es并建立倒排索引(前提是已经手动创建好映射）
        EsService esService = EsServiceImpl.getInstance();
        esService.save(hospitals);
    }
}
