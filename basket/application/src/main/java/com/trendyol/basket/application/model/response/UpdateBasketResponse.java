package com.trendyol.basket.application.model.response;

import com.trendyol.basket.application.model.dto.BasketDTO;

public class UpdateBasketResponse {
    private BasketDTO basketDTO;

    public UpdateBasketResponse(BasketDTO basketDTO) {
        this.basketDTO = basketDTO;
    }

    public BasketDTO getBasketDTO() {
        return basketDTO;
    }

    public void setBasketDTO(BasketDTO basketDTO) {
        this.basketDTO = basketDTO;
    }
}
