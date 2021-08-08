package com.trendyol.product.service;

import com.trendyol.product.kafkamodel.PriceChangeModel;
import com.trendyol.product.kafkamodel.StockChangeModel;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String msg, String topic){
        kafkaTemplate.send(topic, msg);
    }

    public void sendChangePriceMessage(PriceChangeModel msg, String topic){
        kafkaTemplate.send(topic, msg);
    }
    public void sendChangeStockChangeMessage(StockChangeModel msg, String topic){
        kafkaTemplate.send(topic, msg);
    }
}
