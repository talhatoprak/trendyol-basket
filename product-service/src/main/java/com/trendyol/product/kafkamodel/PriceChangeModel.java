package com.trendyol.product.kafkamodel;

import java.math.BigDecimal;

public class PriceChangeModel {
    private String productId;
    private String platform;
    private double oldPrice;
    private double newPrice;

    public PriceChangeModel(String productId, String platform, double oldPrice, double newPrice) {
        this.productId = productId;
        this.platform = platform;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
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
