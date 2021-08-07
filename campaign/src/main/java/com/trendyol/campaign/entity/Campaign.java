package com.trendyol.campaign.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.annotation.Generated;
import java.math.BigDecimal;
@RedisHash("Campaign")
public class Campaign {
    @Id
    private String id;
    private String title;
    private BigDecimal discount;
    private BigDecimal minPrice;

    public Campaign() {
    }

    public Campaign(String title, BigDecimal discount, BigDecimal minPrice) {
        this.title = title;
        this.discount = discount;
        this.minPrice = minPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }
}
