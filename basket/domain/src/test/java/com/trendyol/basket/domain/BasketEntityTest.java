package com.trendyol.basket.domain;


import com.trendyol.basket.domain.entity.Basket;
import com.trendyol.basket.domain.entity.BasketInfo;
import com.trendyol.basket.domain.entity.BasketItem;
import com.trendyol.basket.domain.exception.BasketValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BasketEntityTest {
    @Test
    public void when_customerId_less_than_0_while_creating_basket_then_throw_BasketValidationException(){
        BasketItem item=new BasketItem("prod1","title","imageUrl",5,25,8.5);
        BasketValidationException exception = assertThrows(BasketValidationException.class, () ->
               new Basket(0,item)
        );
    }
    @Test
    public void when_customerId_equals_0_while_creating_basket_then_throw_BasketValidationException(){
        BasketItem item=new BasketItem("prod1","title","imageUrl",5,25,8.5);
        BasketValidationException exception = assertThrows(BasketValidationException.class, () ->
                new Basket(0,item)
        );
    }
    @Test
    public void should_add_basket_item(){
        String productId="prod1";
        String productTitle="title";
        String productImage="imageURl1";
        int quantity=5;
        double oldPrice=25.4;
        double newPrice=8.5;
        BasketItem item=new BasketItem(productId,productTitle,productImage,quantity,oldPrice,newPrice);
        Basket basket=  new Basket(1,item);
        assertEquals(basket.getProducts().size(),1);
        assertEquals(basket.getProducts().get(0).getProductId(),productId);
        assertEquals(basket.getProducts().get(0).getTitle(),productTitle);
        assertEquals(basket.getProducts().get(0).getImageUrl(),productImage);
        assertEquals(basket.getProducts().get(0).getQuantity(),quantity);
        assertEquals(basket.getProducts().get(0).getOldPrice(),oldPrice);
        assertEquals(basket.getProducts().get(0).getPrice(),newPrice);
    }

    @Test
    public void should_increase_quantity_when_added_some_product(){
        String productId="prod1";
        String productTitle="title";
        String productImage="imageURl1";
        int item1Quantity=5;
        int item2Quantity=7;
        double oldPrice=25.4;
        double newPrice=8.5;
        BasketItem item=new BasketItem(productId,productTitle,productImage,item1Quantity,oldPrice,newPrice);
        Basket basket=  new Basket(1,item);
        BasketItem item2=new BasketItem(productId,productTitle,productImage,item2Quantity,oldPrice,newPrice);
        basket.addItemToBasket(item2);
        assertEquals(basket.getProducts().size(),1);
        assertEquals(basket.getProducts().get(0).getQuantity(),item1Quantity+item2Quantity);
    }
    @Test
    public void should_calculate_totals_when_added_product(){
        String productId="prod1";
        String productTitle="title";
        String productImage="imageURl1";
        int quantity=5;
        double oldPrice=25.4;
        double newPrice=8.5;
        BasketItem item=new BasketItem(productId,productTitle,productImage,quantity,oldPrice,newPrice);
        Basket basket=  new Basket(1,item);
        BasketInfo basketInfo= basket.getBasketInfo();
        assertEquals(basketInfo.getSubTotal(),newPrice*quantity);
    }
}
