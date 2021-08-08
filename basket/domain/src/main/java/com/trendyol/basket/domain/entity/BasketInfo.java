package com.trendyol.basket.domain.entity;

import com.trendyol.basket.domain.exception.BasketInfoValidationException;

import java.util.ArrayList;
import java.util.List;

public class BasketInfo {
    private double subTotal;
    private BasketCampaign basketCampaigns;
    private double grandTotal;

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

    public double getSubTotal() {
        return subTotal;
    }

    public BasketCampaign getCampaigns() {
        return basketCampaigns;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void updateSubTotal(List<BasketItem> productInfoList){
        subTotal = productInfoList.stream()
                .map(productInfo -> productInfo.getPrice()*productInfo.getQuantity())
                .reduce(0D,Double::sum);
    }

    public void updateGrandTotalWithCampaign(String campaignDisplayName, double campaignPrice){
        if(campaignDisplayName == null || campaignDisplayName.isEmpty()){
            throw new BasketInfoValidationException();
        }
        addCampaign(campaignDisplayName, campaignPrice);
        grandTotal = basketCampaigns.getPrice() + subTotal;
    }

    private void addCampaign(String displayName, double price){
        var campaign = new BasketCampaign(displayName, price);
        basketCampaigns=campaign;
    }



}
