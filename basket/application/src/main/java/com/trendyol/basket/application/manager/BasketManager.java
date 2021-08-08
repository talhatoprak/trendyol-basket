package com.trendyol.basket.application.manager;

import com.trendyol.basket.application.model.request.*;
import com.trendyol.basket.application.model.response.AddToBasketResponse;
import com.trendyol.basket.application.model.response.GetBasketResponse;
import com.trendyol.basket.application.model.response.UpdateBasketResponse;

public interface BasketManager {

    AddToBasketResponse add(AddToBasketRequest addToBasketRequest);
    UpdateBasketResponse update(UpdateBasketRequest updateBasketRequest);
    GetBasketResponse get(GetBasketRequest getBasketRequest);
    void productPriceChange(ChangeProductPriceRequest changeProductPriceRequest);
    void productStockChange(ChangeProductStockRequest changeProductStockRequest);
    void removeItem(RemoveBasketItemRequest removeBasketItemRequest);
}

