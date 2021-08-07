package com.trendyol.basket.application.externalservice.campaign.response;

import com.trendyol.basket.application.model.dto.CampaignDTO;

import java.util.List;

public class GetCampaignResponse {
    private CampaignDTO campaignDTO;

    public GetCampaignResponse(CampaignDTO campaignDTO) {
        this.campaignDTO = campaignDTO;
    }

    public CampaignDTO getCampaignDTO() {
        return campaignDTO;
    }

    public void setCampaignDTOs(CampaignDTO campaignDTO) {
        this.campaignDTO = campaignDTO;
    }
}
