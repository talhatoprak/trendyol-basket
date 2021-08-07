package com.trendyol.basket.application.externalservice.product.request;

public class GetProductRequest {
    private String productId;

    public GetProductRequest(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
