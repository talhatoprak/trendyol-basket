package com.trendyol.basket.application.model.response;

import com.trendyol.basket.application.model.dto.BasketDTO;

public class AddToBasketResponse {
    private BasketDTO basketDTO;

    public AddToBasketResponse(BasketDTO basketDTO) {
        this.basketDTO = basketDTO;
    }

    public BasketDTO getBasketDTO() {
        return basketDTO;
    }

    public void setBasketDTO(BasketDTO basketDTO) {
        this.basketDTO = basketDTO;
    }
}
