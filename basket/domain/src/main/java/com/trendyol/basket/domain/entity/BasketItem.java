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
    private BigDecimal oldPrice;
    private BigDecimal price;

    public BasketItem() {
    }

    public BasketItem(String productId, String title, String imageUrl, int quantity, BigDecimal oldPrice, BigDecimal price) {
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

    public BigDecimal getPrice() {
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

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public void setPrice(BigDecimal price) {
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
