package com.trendyol.basket.application.manager.impl;

import com.trendyol.basket.application.converter.impl.BasketDtoConverter;
import com.trendyol.basket.application.converter.impl.CampaignProductInfoDtoConverter;
import com.trendyol.basket.application.exception.ProductStockValidationException;
import com.trendyol.basket.application.externalservice.campaign.CampaignService;
import com.trendyol.basket.application.externalservice.campaign.request.GetCampaignRequest;
import com.trendyol.basket.application.externalservice.campaign.response.GetCampaignResponse;
import com.trendyol.basket.application.externalservice.product.ProductService;
import com.trendyol.basket.application.externalservice.product.request.GetProductRequest;
import com.trendyol.basket.application.manager.BasketManager;
import com.trendyol.basket.application.model.request.AddToBasketRequest;
import com.trendyol.basket.application.model.request.GetBasketRequest;
import com.trendyol.basket.application.model.request.UpdateBasketRequest;
import com.trendyol.basket.application.model.response.AddToBasketResponse;
import com.trendyol.basket.application.model.response.GetBasketResponse;
import com.trendyol.basket.application.model.response.UpdateBasketResponse;
import com.trendyol.basket.domain.entity.BasketItem;
import com.trendyol.basket.domain.service.BasketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BasketManagerImpl implements BasketManager {
    private final BasketService basketService;
    private final ProductService productService;
    private final CampaignService campaignService;
    private final BasketDtoConverter basketDtoConverter;
    private final CampaignProductInfoDtoConverter campaignProductInfoDtoConverter;

    /*private final UpdateBasketRequestValidator updateBasketRequestValidator;
    private final AddToBasketRequestValidator addToBasketRequestValidator;
    private final GetBasketRequestValidator getBasketRequestValidator;
    */
    public BasketManagerImpl(
            BasketService basketService,
            ProductService productService,
            CampaignService campaignService,
            BasketDtoConverter basketDtoConverter,
            CampaignProductInfoDtoConverter campaignProductInfoDtoConverter
            /*UpdateBasketRequestValidator updateBasketRequestValidator,
            AddToBasketRequestValidator addToBasketRequestValidator,
            GetBasketRequestValidator getBasketRequestValidator,
            */) {
        this.basketService = basketService;
        this.productService = productService;
        this.campaignService = campaignService;
        this.basketDtoConverter = basketDtoConverter;
        this.campaignProductInfoDtoConverter = campaignProductInfoDtoConverter;
      /*  this.updateBasketRequestValidator = updateBasketRequestValidator;
        this.addToBasketRequestValidator = addToBasketRequestValidator;
        this.getBasketRequestValidator = getBasketRequestValidator;
        */
    }

    @Override
    public AddToBasketResponse add(AddToBasketRequest addToBasketRequest) {
        //addToBasketRequestValidator.validate(addToBasketRequest);
        var getProductRequest = new GetProductRequest(addToBasketRequest.getProductId());
        var productResponse = productService.get(getProductRequest);

        validateProductStock(productResponse.getProductDTO().getQuantity(), addToBasketRequest.getQuantity());
        var basket = basketService.add(
                addToBasketRequest.getCustomerId(),
                productResponse.getProductDTO().getId(),
                productResponse.getProductDTO().getImageUrl(),
                productResponse.getProductDTO().getTitle(),
                productResponse.getProductDTO().getQuantity(),
                productResponse.getProductDTO().getPrice(),
                productResponse.getProductDTO().getOldPrice());
        var getCampaignRequest = prepareGetCampaignRequest(basket.getCustomerId(), basket.getProducts());
        var campaignResponse = campaignService.get(getCampaignRequest);
        UpdateBasketInfoWithCampaign(campaignResponse,addToBasketRequest.getCustomerId());

        return new AddToBasketResponse();
    }

    @Override
    public UpdateBasketResponse update(UpdateBasketRequest updateBasketRequest) {
        //updateBasketRequestValidator.validate(updateBasketRequest);
        var getProductRequest = new GetProductRequest(updateBasketRequest.getProductId());
        var productResponse = productService.get(getProductRequest);
        validateProductStock(productResponse.getProductDTO().getQuantity(), updateBasketRequest.getQuantity());
        var basket = basketService.update(
                updateBasketRequest.getCustomerId(),
                updateBasketRequest.getProductId(),
                updateBasketRequest.getQuantity());
        var getCampaignRequest = prepareGetCampaignRequest(basket.getCustomerId(), basket.getProducts());
        var campaignResponse = campaignService.get(getCampaignRequest);
        UpdateBasketInfoWithCampaign(campaignResponse,updateBasketRequest.getCustomerId());

        return new UpdateBasketResponse();
    }

    private void validateProductStock(int existsStock, int stockToBeAdded) {
        if (existsStock < stockToBeAdded) {
            throw new ProductStockValidationException();
        }
    }

    private GetCampaignRequest prepareGetCampaignRequest(long customerId, List<BasketItem> productInfos) {
        var getCampaignProductDTOs = productInfos.stream()
                .map(campaignProductInfoDtoConverter::convert)
                .collect(Collectors.toList());
        var getCampaignRequest = new GetCampaignRequest(customerId, getCampaignProductDTOs);
        return getCampaignRequest;
    }

    private void UpdateBasketInfoWithCampaign(GetCampaignResponse getCampaignResponse,long customerId) {
        basketService.clearBasketCampaigns(customerId);
        getCampaignResponse.getCampaignDTOs().forEach(campaignDTO -> {
            basketService.addCampaignToBasket(customerId, campaignDTO.getDisplayName(), campaignDTO.getPrice());
        });
    }

    @Override
    public GetBasketResponse get(GetBasketRequest getBasketRequest) {
        //getBasketRequestValidator.validate(getBasketRequest);
        var basket = basketService.getByCustomerId(getBasketRequest.getCustomerId());
        var basketDTO = basketDtoConverter.convert(basket);
        return new GetBasketResponse(basketDTO);
    }
}
