package com.trendyol.basket.application.externalservice.product;

import com.trendyol.basket.application.externalservice.product.request.GetProductRequest;
import com.trendyol.basket.application.externalservice.product.response.GetProductResponse;

public interface ProductService {
    GetProductResponse get(GetProductRequest getProductRequest);
}
