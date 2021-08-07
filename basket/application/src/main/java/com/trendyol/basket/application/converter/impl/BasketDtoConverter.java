package com.trendyol.basket.application.converter.impl;

import com.trendyol.basket.application.converter.Converter;
import com.trendyol.basket.application.model.dto.BasketDTO;
import com.trendyol.basket.domain.entity.Basket;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BasketDtoConverter implements Converter<Basket, BasketDTO> {

    private BasketInfoDtoConverter basketInfoDtoConverter;
    private BasketItemDtoConverter productInfoDtoConverter;

    public BasketDtoConverter(
            BasketInfoDtoConverter basketInfoDtoConverter,
            BasketItemDtoConverter productInfoDtoConverter){
        this.basketInfoDtoConverter = basketInfoDtoConverter;
        this.productInfoDtoConverter = productInfoDtoConverter;
    }

    @Override
    public BasketDTO convert(Basket basket) {
        var basketDTO = new BasketDTO();
        var basketInfoDTO = basketInfoDtoConverter.convert(basket.getBasketInfo());
        basketDTO.setBasketInfoDTO(basketInfoDTO);
        var productInfoDTOs = basket.getProducts().stream()
                .map(productInfoDtoConverter::convert)
                .collect(Collectors.toList());
        basketDTO.setProducts(productInfoDTOs);
        return basketDTO;
    }
}
