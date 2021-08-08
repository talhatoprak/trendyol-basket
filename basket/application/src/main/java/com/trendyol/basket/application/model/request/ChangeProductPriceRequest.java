package com.trendyol.basket.application.model.request;

import java.math.BigDecimal;

public class ChangeProductPriceRequest {
    private String productId;
    private double oldPrice;
    private double newPrice;

    public ChangeProductPriceRequest(String productId, double oldPrice, double newPrice) {
        this.productId = productId;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }
}
