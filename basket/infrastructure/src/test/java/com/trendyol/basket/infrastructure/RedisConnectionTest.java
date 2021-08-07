package com.trendyol.basket.infrastructure;

import com.trendyol.basket.application.manager.BasketManager;
import com.trendyol.basket.application.model.request.AddToBasketRequest;
import com.trendyol.basket.application.model.response.AddToBasketResponse;
import com.trendyol.basket.infrastructure.repository.BasketRepositoryImpl;
import com.trendyol.basket.infrastructure.repository.jpaRepository.BasketCrudRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration
@SpringBootTest
public class RedisConnectionTest {
    private final BasketCrudRepository basketCrudRepository;
    private final BasketManager basketManager;
    public RedisConnectionTest(@Autowired BasketCrudRepository basketCrudRepository,@Autowired  BasketManager basketManager) {

        this.basketCrudRepository = basketCrudRepository;
        this.basketManager = basketManager;
    }

    @Test
    public void createBasket(){
       // Basket basket=new Basket(1,new BasketItem(11,"prod1","img",5, BigDecimal.valueOf(10),BigDecimal.valueOf(7)));
       BasketRepositoryImpl basketRepository=new BasketRepositoryImpl(basketCrudRepository);
        // basketRepository.save(basket);
        var response= basketRepository.findByProductId("11");
        System.out.println(response.toString());
    }
    @Test
    public void findAllBasket(){
        var response=this.basketCrudRepository.findAll();
        response.forEach(basket -> System.out.println(basket.toString()));
    }
    @Test
    public void addToBasket(){
        AddToBasketRequest request=new AddToBasketRequest();
        request.setCustomerId(2);
        request.setQuantity(2);
        request.setProductId("d16966bc-dc34-40cc-8743-cc4cd98168e3");
        AddToBasketResponse response= basketManager.add(request);
        //assertTrue(response.g);

    }
}
