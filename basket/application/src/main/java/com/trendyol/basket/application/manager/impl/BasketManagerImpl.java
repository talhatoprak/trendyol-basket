package com.trendyol.basket.application.manager.impl;

import com.trendyol.basket.application.converter.impl.BasketDtoConverter;
import com.trendyol.basket.application.converter.impl.CampaignProductInfoDtoConverter;
import com.trendyol.basket.application.exception.ProductStockValidationException;
import com.trendyol.basket.application.externalservice.campaign.CampaignService;
import com.trendyol.basket.application.externalservice.campaign.model.CampaignProductInfoDTO;
import com.trendyol.basket.application.externalservice.campaign.request.GetCampaignRequest;
import com.trendyol.basket.application.externalservice.campaign.response.GetCampaignResponse;
import com.trendyol.basket.application.externalservice.customer.CustomerService;
import com.trendyol.basket.application.externalservice.customer.response.GetCustomerResponse;
import com.trendyol.basket.application.externalservice.email.EmailService;
import com.trendyol.basket.application.externalservice.product.ProductService;
import com.trendyol.basket.application.externalservice.product.request.GetProductRequest;
import com.trendyol.basket.application.manager.BasketManager;
import com.trendyol.basket.application.model.dto.CampaignDTO;
import com.trendyol.basket.application.model.request.*;
import com.trendyol.basket.application.model.response.AddToBasketResponse;
import com.trendyol.basket.application.model.response.GetBasketResponse;
import com.trendyol.basket.application.model.response.UpdateBasketResponse;
import com.trendyol.basket.domain.entity.Basket;
import com.trendyol.basket.domain.entity.BasketItem;
import com.trendyol.basket.domain.exception.ProductNotFoundException;
import com.trendyol.basket.domain.service.BasketService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BasketManagerImpl implements BasketManager {
    private final BasketService basketService;
    private final ProductService productService;
    private final CampaignService campaignService;
    private final BasketDtoConverter basketDtoConverter;
    private final CampaignProductInfoDtoConverter campaignProductInfoDtoConverter;
    private final EmailService emailService;
    private final CustomerService customerService;

    /*private final UpdateBasketRequestValidator updateBasketRequestValidator;
    private final AddToBasketRequestValidator addToBasketRequestValidator;
    private final GetBasketRequestValidator getBasketRequestValidator;
    */
    public BasketManagerImpl(
            BasketService basketService,
            ProductService productService,
            CampaignService campaignService,
            BasketDtoConverter basketDtoConverter,
            CampaignProductInfoDtoConverter campaignProductInfoDtoConverter,
            EmailService emailService,
            /*UpdateBasketRequestValidator updateBasketRequestValidator,
            AddToBasketRequestValidator addToBasketRequestValidator,
            GetBasketRequestValidator getBasketRequestValidator,
            */CustomerService customerService) {
        this.basketService = basketService;
        this.productService = productService;
        this.campaignService = campaignService;
        this.basketDtoConverter = basketDtoConverter;
        this.campaignProductInfoDtoConverter = campaignProductInfoDtoConverter;
        this.emailService=emailService;
      /*  this.updateBasketRequestValidator = updateBasketRequestValidator;
        this.addToBasketRequestValidator = addToBasketRequestValidator;
        this.getBasketRequestValidator = getBasketRequestValidator;
        */
        this.customerService = customerService;
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
                addToBasketRequest.getQuantity(),
                productResponse.getProductDTO().getPrice(),
                productResponse.getProductDTO().getOldPrice());
        var getCampaignRequest = prepareGetCampaignRequest(basket.getCustomerId(), basket.getProducts());
        var campaignResponse = campaignService.get(getCampaignRequest);
        UpdateBasketInfoWithCampaign(campaignResponse, addToBasketRequest.getCustomerId());

        return new AddToBasketResponse(basketDtoConverter.convert(basket));
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
        basket=UpdateBasketInfoWithCampaign(campaignResponse, updateBasketRequest.getCustomerId());

        return new UpdateBasketResponse(basketDtoConverter.convert(basket));
    }

    private void validateProductStock(int existsStock, int stockToBeAdded) {
        if (existsStock < stockToBeAdded) {
            throw new ProductStockValidationException();
        }
    }

    private GetCampaignRequest prepareGetCampaignRequest(long customerId, List<BasketItem> productInfos) {
        List<CampaignProductInfoDTO> productInfoDTOS = new ArrayList<>();
        BigDecimal productTotalPrice = BigDecimal.valueOf(0);
        for (int i = 0; i < productInfos.size(); i++) {
            BasketItem product = productInfos.get(i);
            productInfoDTOS.add(campaignProductInfoDtoConverter.convert(product));
            double total = product.getQuantity() * product.getPrice();
            productTotalPrice = productTotalPrice.add(BigDecimal.valueOf(total));
        }
        var getCampaignProductDTOs = productInfos.stream()
                .map(campaignProductInfoDtoConverter::convert)
                .collect(Collectors.toList());
        var getCampaignRequest = new GetCampaignRequest(customerId, getCampaignProductDTOs, productTotalPrice);
        return getCampaignRequest;
    }

    private Basket UpdateBasketInfoWithCampaign(GetCampaignResponse getCampaignResponse, long customerId) {
        CampaignDTO campaign = getCampaignResponse.getCampaignDTO();
        return basketService.addCampaignToBasket(customerId, campaign.getDisplayName(), campaign.getPrice());
    }

    @Override
    public GetBasketResponse get(GetBasketRequest getBasketRequest) {
        //getBasketRequestValidator.validate(getBasketRequest);
        var basket = basketService.getByCustomerId(getBasketRequest.getCustomerId());
        var basketDTO = basketDtoConverter.convert(basket);
        return new GetBasketResponse(basketDTO);
    }

    @Override
    public void productPriceChange(ChangeProductPriceRequest changeProductPriceRequest) {
        String productId= changeProductPriceRequest.getProductId();
        double oldPrice=changeProductPriceRequest.getOldPrice();
        double price=changeProductPriceRequest.getNewPrice();
        List<Basket> basketList=basketService.getByProductId(changeProductPriceRequest.getProductId());
        if(basketList.size()==0)
            return;
        BasketItem item=basketList.get(0).getProducts()
                .stream()
                .filter(f->f.getProductId()
                        .equals(productId)).findFirst().orElseThrow(ProductNotFoundException::new);
        for (int i = 0; i < basketList.size(); i++) {
            Basket basket=basketList.get(i);
            basket.setProductPrice(productId, price,oldPrice);
            basket=basketService.save(basket);
            var getCampaignRequest = prepareGetCampaignRequest(basket.getCustomerId(), basket.getProducts());
            var campaignResponse = campaignService.get(getCampaignRequest);
            basket=UpdateBasketInfoWithCampaign(campaignResponse, basket.getCustomerId());
            if(oldPrice>price){
                sendPriceDropMail(basket.getCustomerId(),item.getTitle(),oldPrice,price);
            }
        }
    }

    @Override
    public void productStockChange(ChangeProductStockRequest changeProductStockRequest) {
        String productId= changeProductStockRequest.getProductId();
        int oldQuantity=changeProductStockRequest.getOldQuantity();
        int newQuantity=changeProductStockRequest.getNewQuantity();
        List<Basket> basketList=basketService.getByProductId(changeProductStockRequest.getProductId());
        for (int i = 0; i < basketList.size(); i++) {
            Basket basket = basketList.get(i);
            BasketItem item = basket.getProducts().stream()
                    .filter(productInfo -> productInfo.getProductId().equals(productId))
                    .findFirst()
                    .orElseThrow(ProductNotFoundException::new);
            if (item.getQuantity() > newQuantity) {
                basket.setProductQuantity(productId, newQuantity);
                basketService.save(basket);
            }
            if(item.getQuantity()==0){
                sendOutOfStockMail(basket.getCustomerId(), item.getTitle());
            }
            else if(item.getQuantity()<=3){
                sendRemainingStockMail(basket.getCustomerId(), item.getTitle(),item.getQuantity());
            }
        }
    }

    @Override
    public void removeItem(RemoveBasketItemRequest removeBasketItemRequest) {
        Basket basket = basketService.removeBasketItem(removeBasketItemRequest.getCustomerId(), removeBasketItemRequest.getProductId(), removeBasketItemRequest.getCount());
        var getCampaignRequest = prepareGetCampaignRequest(basket.getCustomerId(), basket.getProducts());
        var campaignResponse = campaignService.get(getCampaignRequest);
        UpdateBasketInfoWithCampaign(campaignResponse, removeBasketItemRequest.getCustomerId());
    }

    public void sendPriceDropMail(long customerId,String productName,double oldPrice,double newPrice){
       GetCustomerResponse response= customerService.get(customerId);
       String text="Sayın "+response.getFullName()+", sepetinize eklediğiniz "+productName+" adlı ürünün fiyatı "+oldPrice+"' den "+newPrice+"'ye düşmüştür.";
       emailService.sendSimpleMessage(response.getEmail(),"Sepetinizdeki ürünün fiyatı düştü",text);
    }
    public void sendOutOfStockMail(long customerId,String productName){
        GetCustomerResponse response= customerService.get(customerId);
        String text=String.format("Sayın %s, sepetinize eklediğiniz %s adlı ürünün stoklarımızda kalmamıştır.",
                response.getFullName(),
                productName);
        emailService.sendSimpleMessage(response.getEmail(),"Sepetinizdeki ürünün stokta kalmamıştır",text);
    }
    public void sendRemainingStockMail(long customerId,String productName,int quantity){
        GetCustomerResponse response= customerService.get(customerId);

        String text="Sayın "+response.getFullName()+", sepetinize eklediğiniz "+productName+" adlı ürün son "+quantity+" adet kalmıştır.";
        emailService.sendSimpleMessage(response.getEmail(),"Sepetinizdeki ürünün stokta kalmamıştır",text);
    }
}
