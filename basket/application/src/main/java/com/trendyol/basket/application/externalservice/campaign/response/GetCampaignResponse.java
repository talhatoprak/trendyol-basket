package com.trendyol.basket.application.externalservice.campaign.response;

import com.trendyol.basket.application.model.dto.CampaignDTO;

import java.util.List;

public class GetCampaignResponse {
    private List<CampaignDTO> campaignDTOs;

    public List<CampaignDTO> getCampaignDTOs() {
        return campaignDTOs;
    }

    public void setCampaignDTOs(List<CampaignDTO> campaignDTOs) {
        this.campaignDTOs = campaignDTOs;
    }
}
