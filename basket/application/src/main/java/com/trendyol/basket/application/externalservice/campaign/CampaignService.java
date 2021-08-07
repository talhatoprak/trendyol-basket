package com.trendyol.basket.application.externalservice.campaign;

import com.trendyol.basket.application.externalservice.campaign.request.GetCampaignRequest;
import com.trendyol.basket.application.externalservice.campaign.response.GetCampaignResponse;

public interface CampaignService {
    GetCampaignResponse get(GetCampaignRequest getCampaignRequest);
}

