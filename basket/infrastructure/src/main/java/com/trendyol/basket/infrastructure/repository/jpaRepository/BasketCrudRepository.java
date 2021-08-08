package com.trendyol.basket.infrastructure.repository.jpaRepository;

import com.trendyol.basket.domain.entity.Basket;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "basket",viewName = "all")
public interface BasketCrudRepository extends CouchbaseRepository<Basket, Long> {
    @Query("#{#n1ql.selectEntity} #{#n1ql.bucket}  WHERE #{#n1ql.filter} and ARRAY_CONTAINS(followerUserIds, $1)")
    Optional<List<Basket>> findByProductId(String id);

   // Optional<List<Basket>> findAllByProductsIsInProductId(String id);

    Optional<Basket> findByCustomerId(Long aLong);
}
