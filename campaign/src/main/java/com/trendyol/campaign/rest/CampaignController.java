package com.trendyol.campaign.rest;

import com.trendyol.campaign.entity.Campaign;
import com.trendyol.campaign.model.campaign.request.GetCampaignRequest;
import com.trendyol.campaign.model.campaign.response.CampaignResponse;
import com.trendyol.campaign.service.CampaignService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("campaign")
public class CampaignController {
    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }
    @PostMapping
    public Campaign create(@RequestBody Campaign campaign){
        return campaignService.create(campaign);
    }
    @PostMapping("/update")
    public Campaign update(@RequestBody Campaign campaign){
        return campaignService.update(campaign);
    }
    @GetMapping("/{campaignId}")
    public Campaign findById(@PathVariable String campaignId){
        return campaignService.findById(campaignId);
    }

    @GetMapping("/findByMinPrice/{minPrice}")
    public Campaign findByMinPrice(BigDecimal minPrice){
        return campaignService.findByMinPrice(minPrice);
    }
    @PostMapping("/getCampaign")
    public CampaignResponse getCampaignResponse(@RequestBody GetCampaignRequest campaignRequest){
        return campaignService.getCampaignResponseByCampaignRequest(campaignRequest);
    }
}
