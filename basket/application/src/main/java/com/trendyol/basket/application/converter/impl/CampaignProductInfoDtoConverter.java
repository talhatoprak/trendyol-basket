package com.trendyol.basket.application.converter.impl;

import com.trendyol.basket.application.converter.Converter;
import com.trendyol.basket.application.externalservice.campaign.model.CampaignProductInfoDTO;
import com.trendyol.basket.application.model.dto.BasketInfoDTO;
import com.trendyol.basket.domain.entity.BasketItem;
import org.springframework.stereotype.Component;

@Component
public class CampaignProductInfoDtoConverter implements Converter<BasketItem, CampaignProductInfoDTO> {
    @Override
    public CampaignProductInfoDTO convert(BasketItem productInfo) {
        return new CampaignProductInfoDTO(productInfo.getProductId(), productInfo.getQuantity());
    }
}
