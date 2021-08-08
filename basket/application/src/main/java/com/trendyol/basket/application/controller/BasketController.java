package com.trendyol.basket.application.controller;

import com.trendyol.basket.application.model.ApiResponse;
import com.trendyol.basket.application.model.request.AddToBasketRequest;
import com.trendyol.basket.application.model.request.RemoveBasketItemRequest;
import com.trendyol.basket.application.model.request.UpdateBasketRequest;
import com.trendyol.basket.application.model.response.AddToBasketResponse;
import com.trendyol.basket.application.model.response.GetBasketResponse;
import com.trendyol.basket.application.model.response.RemoveBasketItemResponse;
import com.trendyol.basket.application.model.response.UpdateBasketResponse;

public interface BasketController {
    ApiResponse<AddToBasketResponse> add(AddToBasketRequest addToBasketRequest);
    ApiResponse<UpdateBasketResponse> update(UpdateBasketRequest updateBasketRequest);
    ApiResponse<GetBasketResponse> get(long customerId);
    void remove(RemoveBasketItemRequest removeBasketItemRequest);
}
