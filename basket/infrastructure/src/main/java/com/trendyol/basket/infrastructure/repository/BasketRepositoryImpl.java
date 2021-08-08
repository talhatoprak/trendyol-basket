package com.trendyol.basket.infrastructure.repository;

import com.trendyol.basket.domain.entity.Basket;
import com.trendyol.basket.domain.repository.BasketRepository;
import com.trendyol.basket.infrastructure.repository.jpaRepository.BasketCrudRepository;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BasketRepositoryImpl implements  BasketRepository  {

    private final BasketCrudRepository basketCrudRepository;

    public BasketRepositoryImpl(BasketCrudRepository basketCrudRepository) {
        this.basketCrudRepository = basketCrudRepository;
    }

    @Override
    public Optional<Basket> findByCustomerId(long customerId) {
        return basketCrudRepository.findById(customerId);
    }

    @Override
    public void save(Basket basket) {
        this.basketCrudRepository.save(basket);
    }

    @Override
    public Optional<List<Basket>> findByProductId(String productId) {
        Optional<List<Basket>> optionalBasketList=basketCrudRepository.findByProductId(productId);
        var list = optionalBasketList.get();

        return optionalBasketList;
    }
}
