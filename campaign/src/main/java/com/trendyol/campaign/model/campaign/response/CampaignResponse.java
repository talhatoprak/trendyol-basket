package com.trendyol.campaign.model.campaign.response;

import java.math.BigDecimal;

public class CampaignResponse {
    private String displayName;
    private BigDecimal price;

    public CampaignResponse(String displayName, BigDecimal price) {
        this.displayName = displayName;
        this.price = price;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
