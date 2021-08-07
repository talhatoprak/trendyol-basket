package com.trendyol.basket.domain.repository;

import com.trendyol.basket.domain.entity.Basket;

import java.util.List;
import java.util.Optional;


public interface BasketRepository {
    Optional<Basket> findByCustomerId(long customerId);
    void save(Basket basket);
    Optional<List<Basket>> findByProductId(String productId);
}
