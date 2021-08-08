package com.trendyol.basket.infrastructure.listener;

import com.trendyol.basket.application.manager.BasketManager;
import com.trendyol.basket.application.model.events.PriceChangeEvent;
import com.trendyol.basket.application.model.events.StockChangeEvent;
import com.trendyol.basket.application.model.request.ChangeProductPriceRequest;
import com.trendyol.basket.application.model.request.ChangeProductStockRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class EventListener {

    private final BasketManager basketManager;

    public EventListener(BasketManager basketManager) {
        this.basketManager = basketManager;
    }

    @KafkaListener(topics = "changeSalesPrice", groupId = "group-id", containerFactory = "kafkaListenerContainerFactoryPriceChangeEvent")
    public void listenPriceChange(PriceChangeEvent event)
    {
        System.out.format("priceChangeEvent: %s\n", event.toString());
        BigDecimal oldPrice=BigDecimal.valueOf(event.getOldPrice());
        BigDecimal newPrice=BigDecimal.valueOf(event.getNewPrice());
        basketManager.productPriceChange(new ChangeProductPriceRequest(event.getProductId(),oldPrice,newPrice));


    }
    @KafkaListener(topics = "changeProductStock", groupId = "group-id", containerFactory = "kafkaListenerContainerFactoryStockChangeEvent")
    public void listenSendEmailNotification(StockChangeEvent event)
    {
        System.out.format("stockChangeEvent: %s\n", event.toString());
        basketManager.productStockChange(new ChangeProductStockRequest(event.getProductId(),event.getOldQuantity(),event.getNewQuantity()));
    }
}
