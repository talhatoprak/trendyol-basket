package com.trendyol.campaign.model.campaign.dto;

public class CampaignProductInfoDTO {
    private String productId;
    private int quantity;

    public CampaignProductInfoDTO(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
