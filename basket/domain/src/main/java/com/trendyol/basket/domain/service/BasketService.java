package com.trendyol.basket.domain.service;

import com.trendyol.basket.domain.entity.Basket;

import java.math.BigDecimal;
import java.util.List;

public interface BasketService {
    Basket add(long customerId, String productId, String imageUrl, String title, int quantity, BigDecimal price, BigDecimal oldPrice);
    Basket update(long customerId, String productId, int quantity);
    Basket getByCustomerId(long customerId);
    void addCampaignToBasket(long customerId, String campaignDisplayName, BigDecimal campaignPrice);
    List<Basket> getByProductId(String productId);
    void changeProductPrice(String productId,BigDecimal price);

    void changeProductStock(String productId, int newQuantity);
}
