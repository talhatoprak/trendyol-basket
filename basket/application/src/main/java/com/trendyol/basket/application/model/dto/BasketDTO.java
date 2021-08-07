package com.trendyol.basket.application.model.dto;

import java.util.List;

public class BasketDTO {
    private List<BasketItemDTO> products;
    private BasketInfoDTO basketInfoDTO;

    public List<BasketItemDTO> getProducts() {
        return products;
    }

    public void setProducts(List<BasketItemDTO> products) {
        this.products = products;
    }

    public BasketInfoDTO getBasketInfoDTO() {
        return basketInfoDTO;
    }

    public void setBasketInfoDTO(BasketInfoDTO basketInfoDTO) {
        this.basketInfoDTO = basketInfoDTO;
    }
}
