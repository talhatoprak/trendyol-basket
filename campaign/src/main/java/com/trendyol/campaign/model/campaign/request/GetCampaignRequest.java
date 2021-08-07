package com.trendyol.campaign.model.campaign.request;

import com.trendyol.campaign.model.campaign.dto.CampaignProductInfoDTO;

import java.math.BigDecimal;
import java.util.List;

public class GetCampaignRequest {
    private long customerId;
    private List<CampaignProductInfoDTO> productInfoDTOs;
    private BigDecimal price;

    public GetCampaignRequest(long customerId, List<CampaignProductInfoDTO> productInfoDTOList) {
        this.customerId = customerId;
        this.productInfoDTOs = productInfoDTOList;
    }

    public long getCustomerId() {
        return customerId;
    }

    public List<CampaignProductInfoDTO> getProductInfoDTOs() {
        return productInfoDTOs;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public void setProductInfoDTOs(List<CampaignProductInfoDTO> productInfoDTOs) {
        this.productInfoDTOs = productInfoDTOs;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
