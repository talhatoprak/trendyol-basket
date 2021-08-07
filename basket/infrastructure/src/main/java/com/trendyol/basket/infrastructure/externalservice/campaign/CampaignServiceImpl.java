package com.trendyol.basket.infrastructure.externalservice.campaign;

import com.trendyol.basket.application.externalservice.campaign.CampaignService;
import com.trendyol.basket.application.externalservice.campaign.request.GetCampaignRequest;
import com.trendyol.basket.application.externalservice.campaign.response.GetCampaignResponse;
import org.springframework.stereotype.Service;

@Service
public class CampaignServiceImpl implements CampaignService {
    @Override
    public GetCampaignResponse get(GetCampaignRequest getCampaignRequest) {
        return null;
    }
}
