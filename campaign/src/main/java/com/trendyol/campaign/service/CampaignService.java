package com.trendyol.campaign.service;

import com.trendyol.campaign.entity.Campaign;
import com.trendyol.campaign.model.campaign.request.GetCampaignRequest;
import com.trendyol.campaign.model.campaign.response.CampaignResponse;
import com.trendyol.campaign.repository.CampaignRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CampaignService {
    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public Campaign create(Campaign campaign) {
        return campaignRepository.save(campaign);
    }
    public Campaign findById(String campaignId){
        return campaignRepository.findById(campaignId).orElseThrow(()->new RuntimeException("Campaign not found"));
    }

    public List<Campaign> getAll() {
        var list = campaignRepository.findAll().iterator();
        List<Campaign> campaignList = new ArrayList<>();
        while (list.hasNext()) {
            campaignList.add(list.next());
        }
        return campaignList;
    }

    public Campaign findByMinPrice(BigDecimal price) {
        /*Optional<Campaign> campaignOptional=this.campaignRepository.findByMinPriceGreaterThan(price);
        if(campaignOptional.isEmpty())
            throw new RuntimeException("Campaign Not Found");
        return campaignOptional.get();*/
        List<Campaign> campaignList = getAll().
                stream().
                sorted((campaign1,campaign2)->campaign1.getMinPrice().
                compareTo(campaign2.getMinPrice())).
                collect(Collectors.toList());
        Campaign selectedCampaign = null;
        for (int i = 0; i < campaignList.size(); i++) {
            Campaign indexCampaign = campaignList.get(i);
            if (indexCampaign.getMinPrice().compareTo(price)<1) {
                    selectedCampaign = campaignList.get(i);
            }
        }
        if (selectedCampaign == null)
            throw new RuntimeException("Campaign Not Found");
        return selectedCampaign;
    }

    public Campaign update(Campaign campaign) {
        Campaign campaignRecord=findById(campaign.getId());
        campaignRecord.setDiscount(campaign.getDiscount());
        campaignRecord.setMinPrice(campaign.getMinPrice());
        campaignRecord.setTitle(campaign.getTitle());
        campaignRepository.save(campaignRecord);
        return campaignRecord;
    }

    public CampaignResponse getCampaignResponseByCampaignRequest(GetCampaignRequest campaignRequest) {
        BigDecimal total=campaignRequest.getPrice();
        Campaign campaign=findByMinPrice(total);
        return new CampaignResponse(campaign.getTitle(),campaign.getDiscount());
    }
}
