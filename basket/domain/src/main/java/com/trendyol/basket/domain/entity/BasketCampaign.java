package com.trendyol.basket.domain.entity;

import java.math.BigDecimal;

public class BasketCampaign {
    private String displayName;
    private double price;

    public BasketCampaign(String displayName, double price) {
        this.displayName = displayName;
        this.price = price;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getPrice() {
        return price;
    }
}
