package com.trendyol.basket.application.model.dto;

import java.math.BigDecimal;

public class CampaignDTO {
    private String displayName;
    private double price;

    public CampaignDTO() {
    }

    public CampaignDTO(String displayName, double price) {
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
