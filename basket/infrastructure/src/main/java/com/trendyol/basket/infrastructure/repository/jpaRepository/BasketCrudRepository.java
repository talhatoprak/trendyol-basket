package com.trendyol.basket.infrastructure.repository.jpaRepository;

import com.trendyol.basket.domain.entity.Basket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketCrudRepository extends CrudRepository<Basket, Long> {
    Optional<List<Basket>> findByProductsProductId(String id);

    Optional<List<Basket>> findAllByProductsIsInProductId(String id);

    Optional<Basket> findByCustomerId(Long aLong);
}
