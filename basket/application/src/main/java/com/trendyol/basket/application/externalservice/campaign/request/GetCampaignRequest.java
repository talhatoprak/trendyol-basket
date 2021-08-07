package com.trendyol.basket.application.externalservice.campaign.request;

import com.trendyol.basket.application.externalservice.campaign.model.CampaignProductInfoDTO;

import java.math.BigDecimal;
import java.util.List;

public class GetCampaignRequest {
    private long customerId;
    private List<CampaignProductInfoDTO> productInfoDTOs;
    private BigDecimal price;

    public GetCampaignRequest(long customerId, List<CampaignProductInfoDTO> productInfoDTOList,BigDecimal price) {
        this.customerId = customerId;
        this.productInfoDTOs = productInfoDTOList;
        this.price=price;
    }

    public long getCustomerId() {
        return customerId;
    }

    public List<CampaignProductInfoDTO> getProductInfoDTOList() {
        return productInfoDTOs;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public List<CampaignProductInfoDTO> getProductInfoDTOs() {
        return productInfoDTOs;
    }

    public void setProductInfoDTOs(List<CampaignProductInfoDTO> productInfoDTOs) {
        this.productInfoDTOs = productInfoDTOs;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
