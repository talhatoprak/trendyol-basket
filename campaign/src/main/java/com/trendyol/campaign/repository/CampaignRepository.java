package com.trendyol.campaign.repository;

import com.trendyol.campaign.entity.Campaign;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface CampaignRepository extends CrudRepository<Campaign, String> {
    Optional<Campaign> findByMinPriceGreaterThan(BigDecimal price);
}
