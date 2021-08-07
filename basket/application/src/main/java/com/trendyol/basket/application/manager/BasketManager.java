package com.trendyol.basket.application.manager;

import com.trendyol.basket.application.model.request.AddToBasketRequest;
import com.trendyol.basket.application.model.request.GetBasketRequest;
import com.trendyol.basket.application.model.request.UpdateBasketRequest;
import com.trendyol.basket.application.model.response.AddToBasketResponse;
import com.trendyol.basket.application.model.response.GetBasketResponse;
import com.trendyol.basket.application.model.response.UpdateBasketResponse;

public interface BasketManager {

    AddToBasketResponse add(AddToBasketRequest addToBasketRequest);
    UpdateBasketResponse update(UpdateBasketRequest updateBasketRequest);
    GetBasketResponse get(GetBasketRequest getBasketRequest);
}

