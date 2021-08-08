package com.trendyol.basket.domain.entity;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.math.BigDecimal;

public class BasketItem implements Serializable {
    @Indexed
    private String productId;
    private String title;
    private String imageUrl;
    private int quantity;
    private double oldPrice;
    private double price;

    public BasketItem() {
    }

    public BasketItem(String productId, String title, String imageUrl, int quantity, double oldPrice, double price) {
        this.productId = productId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.oldPrice = oldPrice;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
    public void increaseBasketItem(){
        this.quantity++;
    }
    public void decreaseBasketItem(){
        this.quantity--;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BasketItem{" +
                "productId=" + productId +
                ", title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", quantity=" + quantity +
                ", oldPrice=" + oldPrice +
                ", price=" + price +
                '}';
    }
}
