package com.trendx.ecomm.userservice.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage(String msg,String topic){
        kafkaTemplate.send(topic, msg);
    }

    public void sendMessage(Object msg,String topic){
        kafkaTemplate.send(topic, msg);
    }
}
