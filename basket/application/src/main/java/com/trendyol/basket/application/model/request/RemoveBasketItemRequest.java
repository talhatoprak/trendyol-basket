package com.trendyol.basket.application.model.request;

public class RemoveBasketItemRequest {
    private String productId;
    private long customerId;
    private int count;

    public RemoveBasketItemRequest() {
    }

    public RemoveBasketItemRequest(String productId, long customerId, int count) {
        this.productId = productId;
        this.customerId = customerId;
        this.count = count;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
