package com.trendyol.basket.infrastructure.externalservice.campaign;

import com.trendyol.basket.application.externalservice.campaign.CampaignService;
import com.trendyol.basket.application.externalservice.campaign.request.GetCampaignRequest;
import com.trendyol.basket.application.externalservice.campaign.response.GetCampaignResponse;
import com.trendyol.basket.application.externalservice.product.request.GetProductRequest;
import com.trendyol.basket.application.externalservice.product.response.GetProductResponse;
import com.trendyol.basket.application.model.dto.CampaignDTO;
import com.trendyol.basket.application.model.dto.ProductInfoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {
    private final RestTemplate template;
    @Value("${campaignApiAddress}")
    private String campaignServiceUrl;

    public CampaignServiceImpl(RestTemplate template) {
        this.template = template;
    }

    @Override
    public GetCampaignResponse get(GetCampaignRequest getCampaignRequest){
        String url = campaignServiceUrl + "/campaign/getCampaign";
        CampaignDTO campaign = template.postForObject(url,getCampaignRequest, CampaignDTO.class);
        return new GetCampaignResponse(campaign);
    }

}
