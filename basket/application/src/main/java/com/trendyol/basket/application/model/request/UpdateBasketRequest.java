package com.trendyol.basket.application.model.request;

import java.util.List;

public class UpdateBasketRequest {
    private long customerId;
    private String productId;
    private List<Long> campaignIds;
    private int quantity;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
