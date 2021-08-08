package com.trendyol.basket.domain.service.impl;

import com.trendyol.basket.domain.entity.Basket;
import com.trendyol.basket.domain.entity.BasketItem;
import com.trendyol.basket.domain.exception.BasketNotFoundException;
import com.trendyol.basket.domain.exception.ProductNotFoundException;
import com.trendyol.basket.domain.repository.BasketRepository;
import com.trendyol.basket.domain.service.BasketService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;

    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public Basket add(long customerId, String productId,
                      String imageUrl, String title, int quantity, BigDecimal price, BigDecimal oldPrice) {
        var optionalBasket = basketRepository.findByCustomerId(customerId);
        var productInfo = new BasketItem(productId, imageUrl, title, quantity, price, oldPrice);
        Basket basket;
        if (optionalBasket.isEmpty()) {
            basket = new Basket(customerId, productInfo);
        } else {
            basket = optionalBasket.get();
            var optProductInfo = basket.getProducts().stream().filter(pi -> pi.getProductId().equals(productId)).findFirst();
            if (optProductInfo.isEmpty()) {
                basket.addItemToBasket(productInfo);
            } else {
                var existProductInfo = optProductInfo.get();
                existProductInfo.setQuantity(existProductInfo.getQuantity() + quantity);
            }
        }
        basketRepository.save(basket);
        return basket;
    }

    @Override
    public Basket update(long customerId, String productId, int quantity) {
        var optionalBasket = basketRepository.findByCustomerId(customerId);
        if (optionalBasket.isEmpty())
            throw new BasketNotFoundException();
        var basket = optionalBasket.get();
        basket.setProductQuantity(productId, quantity);
        basketRepository.save(basket);
        return basket;
    }

    @Override
    public Basket getByCustomerId(long customerId) {
        var basket = basketRepository.findByCustomerId(customerId)
                .orElseThrow(BasketNotFoundException::new);
        return basket;
    }

    @Override
    public void addCampaignToBasket(long customerId, String campaignDisplayName, BigDecimal campaignPrice) {
        var optionalBasket = basketRepository.findByCustomerId(customerId);
        if (optionalBasket.isEmpty())
            throw new BasketNotFoundException();
        var basket = optionalBasket.get();
        basket.getBasketInfo().updateGrandTotalWithCampaign(campaignDisplayName, campaignPrice);
        basketRepository.save(basket);
    }


    @Override
    public List<Basket> getByProductId(String productId) {
        var optionalBaskets = basketRepository.findByProductId(productId);
        if (optionalBaskets.isEmpty()) {
            throw new BasketNotFoundException();
        }
        var baskets = optionalBaskets.get();
        return baskets;
    }

    @Override
    public void changeProductPrice(String productId, BigDecimal price) {
        List<Basket> basketList = getByProductId(productId);
        for (int i = 0; i < basketList.size(); i++) {
            Basket basket = basketList.get(i);
            basket.setProductPrice(productId, price);
            basketRepository.save(basket);
        }
    }

    @Override
    public void changeProductStock(String productId, int newQuantity) {
        List<Basket> basketList = getByProductId(productId);
        for (int i = 0; i < basketList.size(); i++) {
            Basket basket = basketList.get(i);
            BasketItem item = basket.getProducts().stream()
                    .filter(productInfo -> productInfo.getProductId().equals(productId))
                    .findFirst()
                    .orElseThrow(ProductNotFoundException::new);
            if (item.getQuantity() > newQuantity) {
                basket.setProductQuantity(productId, newQuantity);
                basketRepository.save(basket);
            }
        }
    }
}
