package com.trendyol.basket.domain.service;

import com.trendyol.basket.domain.entity.Basket;

import java.math.BigDecimal;
import java.util.List;

public interface BasketService {
    Basket add(long customerId, String productId, String imageUrl, String title, int quantity, double price, double oldPrice);
    Basket update(long customerId, String productId, int quantity);
    Basket getByCustomerId(long customerId);
    Basket addCampaignToBasket(long customerId, String campaignDisplayName, double campaignPrice);
    List<Basket> getByProductId(String productId);
    Basket save(Basket basket);

    void changeProductStock(String productId, int newQuantity);

    Basket removeBasketItem(long customerId, String productId, int count);
}
