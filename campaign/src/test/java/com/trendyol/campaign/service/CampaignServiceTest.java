package com.trendyol.campaign.service;

import com.trendyol.campaign.entity.Campaign;
import com.trendyol.campaign.repository.CampaignRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

@ContextConfiguration
@SpringBootTest
public class CampaignServiceTest {
    private final CampaignService campaignService;
    private final CampaignRepository campaignRepository;

    public CampaignServiceTest(@Autowired CampaignService campaignService,@Autowired  CampaignRepository campaignRepository) {
        this.campaignService = campaignService;
        this.campaignRepository = campaignRepository;
    }

    @Test
    public void findbyPrice(){
        //before
        campaignRepository.deleteAll();
        Campaign shippingBaseCampaign=new Campaign("Kargo ücreti:25TL", BigDecimal.valueOf(25),BigDecimal.valueOf(0));
        Campaign shipping100Campaign=new Campaign("100TL üzeri 5TL", BigDecimal.valueOf(5),BigDecimal.valueOf(100));
        Campaign shipping150Campaign=new Campaign("150TL üzeri ücretsiz Kargo", BigDecimal.valueOf(0),BigDecimal.valueOf(150));
        campaignService.create(shippingBaseCampaign);
        campaignService.create(shipping100Campaign);
        campaignService.create(shipping150Campaign);
       var campaign= campaignService.findByMinPrice(BigDecimal.valueOf(65));
       assertEquals(campaign.getId(),shippingBaseCampaign.getId());
        campaign= campaignService.findByMinPrice(BigDecimal.valueOf(99));
        assertEquals(campaign.getId(),shippingBaseCampaign.getId());
        campaign= campaignService.findByMinPrice(BigDecimal.valueOf(65));
        assertEquals(campaign.getId(),shippingBaseCampaign.getId());
        campaign= campaignService.findByMinPrice(BigDecimal.valueOf(100));
        assertEquals(campaign.getId(),shipping100Campaign.getId());
        campaign= campaignService.findByMinPrice(BigDecimal.valueOf(101));
        assertEquals(campaign.getId(),shipping100Campaign.getId());
        campaign= campaignService.findByMinPrice(BigDecimal.valueOf(149));
        assertEquals(campaign.getId(),shipping100Campaign.getId());
        campaign= campaignService.findByMinPrice(BigDecimal.valueOf(150));
        assertEquals(campaign.getId(),shipping150Campaign.getId());
        campaign= campaignService.findByMinPrice(BigDecimal.valueOf(151));
        assertEquals(campaign.getId(),shipping150Campaign.getId());
        campaign= campaignService.findByMinPrice(BigDecimal.valueOf(200));
        assertEquals(campaign.getId(),shipping150Campaign.getId());
        System.out.println(campaign);
    }
}
