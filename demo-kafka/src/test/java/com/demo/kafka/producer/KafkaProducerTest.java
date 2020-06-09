package com.demo.kafka.producer;

import com.alibaba.fastjson.JSON;
import com.demo.kafka.dto.HeartbeatMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Liam(003046)
 * @date 2019/8/17 下午4:13
 */
@Slf4j
public class KafkaProducerTest extends BaseSpringbootTest{

    @Autowired
    private KafkaProducer kafkaProducer;

    @Test
    public void send() {
        Integer executeTimes = 1;
        Integer size = 2000;
        Integer perCount = 1;
        for (int count = 0; count < executeTimes; count++) {
            for (int k = 0; k < size / perCount; k++) {
                for (int i = 0; i < perCount; i++) {
                    HeartbeatMessageDto heartbeatMessageDto = new HeartbeatMessageDto();
                    heartbeatMessageDto.setEdcode("TE" + String.valueOf(111110 + k * perCount + i));
                    heartbeatMessageDto.setAppTime("2019-05-03 22:22:22");
                    heartbeatMessageDto.setHeartTime(new Date().getTime());
                    heartbeatMessageDto.setHeartNum((long) k * perCount + i);
                    kafkaProducer.send("fcboxEdinfoHeartQueue", JSON.toJSONString(heartbeatMessageDto));
                }
            }
        }
    }
}