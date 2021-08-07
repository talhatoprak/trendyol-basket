package com.trendyol.basket.domain.entity;

import com.trendyol.basket.domain.exception.BasketValidationException;
import com.trendyol.basket.domain.exception.ProductNotFoundException;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RedisHash("Basket")
public class Basket implements Serializable {
    @Id
    private long customerId;
    private List<BasketItem> products;
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
        products.add(productInfo);
        basketInfo.updateSubTotal(products);
    }

    public void setProductQuantity(String productId, int quantity) {
        products.stream()
                .filter(productInfo -> productInfo.getProductId() == productId)
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
}