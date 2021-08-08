package com.trendyol.basket.domain.entity;

import com.couchbase.client.java.repository.annotation.Field;
import com.trendyol.basket.domain.exception.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Document
public class Basket implements Serializable {
    @Id
    private long customerId;
    @Field
    private List<BasketItem> products;
    @Field
    private BasketInfo basketInfo;

    public Basket() {
    }

    public Basket(long customerId, List<BasketItem> products, BasketInfo basketInfo) {
        this.customerId = customerId;
        this.products = products;
        this.basketInfo = basketInfo;
    }

    public Basket(long customerId, BasketItem product) {
        var isBasketFieldsNotValid = false;
        isBasketFieldsNotValid =
                customerId <= 0L
                        || product == null;
        if (isBasketFieldsNotValid)
            throw new BasketValidationException();
        this.customerId = customerId;
        this.products = new ArrayList<>();
        products.add(product);
        basketInfo = new BasketInfo(products);
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public void setProducts(List<BasketItem> products) {
        this.products = products;
    }

    public void setBasketInfo(BasketInfo basketInfo) {
        this.basketInfo = basketInfo;
    }

    public long getCustomerId() {
        return customerId;
    }

    public List<BasketItem> getProducts() {
        return products;
    }

    public BasketInfo getBasketInfo() {
        return basketInfo;
    }

    public void addItemToBasket(BasketItem productInfo) {
        var optProductInfo = products.stream()
                .filter(pi -> pi.getProductId().equals(productInfo.getProductId()))
                .findFirst();
        if (optProductInfo.isEmpty()) {
            products.add(productInfo);
        } else {
            var existProductInfo = optProductInfo.get();
            existProductInfo.setQuantity(existProductInfo.getQuantity() + productInfo.getQuantity());
        }
        basketInfo.updateSubTotal(products);
    }

    public void setProductQuantity(String productId, int quantity) {
        products.stream()
                .filter(productInfo -> productInfo.getProductId().equals(productId))
                .findFirst()
                .ifPresentOrElse(productInfo -> productInfo.setQuantity(quantity), ProductNotFoundException::new);
        basketInfo.updateSubTotal(products);

    }

    @Override
    public String toString() {
        String basketString = "Basket{" +
                "customerId=" + customerId;
        if (products.size() > 0) {
            basketString += ", products=[";
            for (int i = 0; i < products.size(); i++) {
                basketString += products.get(i).toString() + ", ";
            }
        }

        basketString += ", basketInfo=" + basketInfo.toString() +
                '}';
        return basketString;
    }

    public void setProductPrice(String productId, double price) {
        products.stream()
                .filter(productInfo -> productInfo.getProductId().equals(productId))
                .findFirst()
                .ifPresentOrElse(productInfo -> productInfo.setPrice(price), ProductNotFoundException::new);
        basketInfo.updateSubTotal(products);

    }
}