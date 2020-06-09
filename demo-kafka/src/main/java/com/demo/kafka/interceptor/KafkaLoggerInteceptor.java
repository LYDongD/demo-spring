package com.demo.kafka.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Liam(003046)
 * @date 2019/8/17 下午4:43
 */
@Slf4j
public class KafkaLoggerInteceptor implements ProducerInterceptor<String, String> {

    /**
     *  发送消息前回调onSend
     */
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        log.info("发送消息：【{}】", record.value());
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
