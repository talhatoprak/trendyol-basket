package com.trendyol.basket.application.converter.impl;

import com.trendyol.basket.application.converter.Converter;
import com.trendyol.basket.application.model.dto.BasketDTO;
import com.trendyol.basket.application.model.dto.BasketItemDTO;
import com.trendyol.basket.domain.entity.BasketItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BasketItemDtoConverter implements Converter<BasketItem, BasketItemDTO> {

    @Override
    public BasketItemDTO convert(BasketItem productInfo) {
        var productInfoDTO = new BasketItemDTO();
        productInfoDTO.setId(productInfo.getProductId());
        productInfoDTO.setPrice(BigDecimal.valueOf(productInfo.getPrice()));
        productInfoDTO.setOldPrice(BigDecimal.valueOf(productInfo.getOldPrice()));
        productInfoDTO.setQuantity(productInfo.getQuantity());
        productInfoDTO.setImageUrl(productInfo.getImageUrl());
        productInfoDTO.setTitle(productInfo.getTitle());
        return productInfoDTO;
    }
}
