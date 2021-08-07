package com.trendyol.basket.application.externalservice.product.response;

import com.trendyol.basket.application.model.dto.ProductInfoDTO;

public class GetProductResponse {
    private ProductInfoDTO productInfoDto;

    public GetProductResponse(ProductInfoDTO productInfoDto) {
        this.productInfoDto = productInfoDto;
    }

    public ProductInfoDTO getProductDTO() {
        return productInfoDto;
    }

    public void setProductDTO(ProductInfoDTO productInfoDto) {
        this.productInfoDto = productInfoDto;
    }
}
