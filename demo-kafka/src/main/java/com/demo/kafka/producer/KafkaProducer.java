package com.demo.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * kafka消息发送者
 * @author Liam(003046)
 * @date 2019/8/17 下午4:11
 */
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(String topicName, String data) {
        kafkaTemplate.send(topicName, data);
    }
}
