package com.trendyol.basket.infrastructure.rest;

import com.trendyol.basket.application.controller.BasketController;
import com.trendyol.basket.application.manager.BasketManager;
import com.trendyol.basket.application.model.ApiResponse;
import com.trendyol.basket.application.model.request.AddToBasketRequest;
import com.trendyol.basket.application.model.request.GetBasketRequest;
import com.trendyol.basket.application.model.request.UpdateBasketRequest;
import com.trendyol.basket.application.model.response.AddToBasketResponse;
import com.trendyol.basket.application.model.response.GetBasketResponse;
import com.trendyol.basket.application.model.response.UpdateBasketResponse;
import com.trendyol.basket.domain.entity.Basket;
import com.trendyol.basket.domain.entity.BasketItem;
import com.trendyol.basket.infrastructure.repository.BasketRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.math.BigDecimal;


@RestController
@RequestMapping("basket")
public class BasketRestController implements BasketController {

    private final BasketManager basketManager;

    public BasketRestController(BasketManager basketManager){
        this.basketManager = basketManager;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @Override
    public ApiResponse<AddToBasketResponse> add(AddToBasketRequest addToBasketRequest) {
        var response = basketManager.add(addToBasketRequest);
        return ApiResponse.ApiResponseBuilderWithData
                .builder()
                .setData(response)
                .build();
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @Override
    public ApiResponse<UpdateBasketResponse> update(UpdateBasketRequest updateBasketRequest) {
        var response = basketManager.update(updateBasketRequest);
        return ApiResponse.ApiResponseBuilderWithData
                .builder()
                .setData(response)
                .build();
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    @Override
    public ApiResponse<GetBasketResponse> get(@PathVariable long customerId) {
      var getBasketRequest = new GetBasketRequest(customerId);
        var response = basketManager.get(getBasketRequest);
        return ApiResponse.ApiResponseBuilderWithData
                .builder()
                .setData(response)
                .build();
    }
}
