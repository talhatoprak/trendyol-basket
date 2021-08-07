package com.trendyol.basket.application.model.request;

public class GetBasketRequest {
    private long customerId;

    public GetBasketRequest(long customerId) {
        this.customerId = customerId;
    }

    public long getCustomerId() {
        return customerId;
    }
}
