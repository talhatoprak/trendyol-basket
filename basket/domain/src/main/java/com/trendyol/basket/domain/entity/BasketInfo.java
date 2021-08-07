package com.trendyol.basket.domain.entity;

import com.trendyol.basket.domain.exception.BasketInfoValidationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BasketInfo {
    private BigDecimal subTotal;
    private List<BasketCampaign> basketCampaigns;
    private BigDecimal grandTotal;

    public BasketInfo() {
    }

    public BasketInfo(List<BasketItem> productInfos){
        var isBasketInfoFieldsNotValid = false;
        isBasketInfoFieldsNotValid =
                productInfos == null || productInfos.size() == 0;
        if(isBasketInfoFieldsNotValid)
            throw new BasketInfoValidationException();
        updateSubTotal(productInfos);
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public List<BasketCampaign> getCampaigns() {
        return basketCampaigns;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void updateSubTotal(List<BasketItem> productInfoList){
        subTotal = productInfoList.stream()
                .map(productInfo -> productInfo.getPrice().multiply(BigDecimal.valueOf(productInfo.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void updateGrandTotalWithCampaign(String campaignDisplayName, BigDecimal campaignPrice){
        if(campaignDisplayName == null || campaignDisplayName.isEmpty()){
            throw new BasketInfoValidationException();
        }
        addCampaign(campaignDisplayName, campaignPrice);
        grandTotal = basketCampaigns.stream()
                .map(BasketCampaign::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add).add(subTotal);
    }

    private void addCampaign(String displayName, BigDecimal price){
        if(basketCampaigns == null){
            basketCampaigns = new ArrayList<>();
        }
        var campaign = new BasketCampaign(displayName, price);
        basketCampaigns.add(campaign);
    }

    public void clearCampaigns(){
        basketCampaigns.clear();
    }

}