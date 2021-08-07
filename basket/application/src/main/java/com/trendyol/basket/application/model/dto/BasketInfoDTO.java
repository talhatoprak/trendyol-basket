package com.trendyol.basket.application.model.dto;

import java.math.BigDecimal;
import java.util.List;

public class BasketInfoDTO {
    private BigDecimal subTotal;
    private List<CampaignDTO> campaignDTOs;
    private BigDecimal grandTotal;

    public List<CampaignDTO> getCampaignDTOs() {
        return campaignDTOs;
    }

    public void setCampaignDTOs(List<CampaignDTO> campaignDTOs) {
        this.campaignDTOs = campaignDTOs;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }
}
