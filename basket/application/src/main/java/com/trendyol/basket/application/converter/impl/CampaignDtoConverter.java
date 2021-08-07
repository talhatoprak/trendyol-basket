package com.trendyol.basket.application.converter.impl;

import com.trendyol.basket.application.converter.Converter;
import com.trendyol.basket.application.model.dto.CampaignDTO;
import com.trendyol.basket.domain.entity.BasketCampaign;
import org.springframework.stereotype.Component;

@Component
public class CampaignDtoConverter implements Converter<BasketCampaign, CampaignDTO> {
    @Override
    public CampaignDTO convert(BasketCampaign basketCampaign) {
        var campaignDTO = new CampaignDTO(basketCampaign.getDisplayName(), basketCampaign.getPrice());
        return campaignDTO;
    }
}
