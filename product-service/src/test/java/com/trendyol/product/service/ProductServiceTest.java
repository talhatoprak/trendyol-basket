package com.trendyol.product.service;


import com.trendyol.product.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ProductServiceTest {

    private final ProductService productService;

    @Autowired
    public ProductServiceTest(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void findById() throws InterruptedException {
        //Given
        String barcode="BR01";
        productService.deleteByBarcode(barcode);
        Product product = new Product();
        product.setBarcode("BR01");
        product.setDescription("Tshirt");
        product.setOldPrice(5.5);
        product.setPrice(5);

        productService.create(product);
        System.out.println("userServiceTest:findById");
        //When
        Product product1 = productService.findById(product.getId());

        //Then
        assertEquals("BR01", product1.getBarcode());
    }

    @Test
    public void ensureProductBarcodeUniqueness() {
        //Given
        String barcode="BR01";
        productService.deleteByBarcode(barcode);
        Product product = new Product();
        product.setBarcode(barcode);
        product.setDescription("Tshirt");
        //product.setMobileSalesPrice(5.5);
        //product.setSalesPrice(5);
        Product product2=new Product();
        product2.setBarcode(barcode);
        product2.setDescription("Short");
        //product2.setMobileSalesPrice(5.7);
        //product2.setSalesPrice(4);
        //When
        productService.create(product);
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                productService.create(product2) //second time
        );
        //Then
        assertEquals("There is already a product barcode as: BR01", exception.getMessage());
    }

    @Test
    public void ensureProductBarcodeNotBeNull(){
        Product product = new Product();
        product.setDescription("Tshirt");
       // product.setMobileSalesPrice(5.5);
        //product.setSalesPrice(5);
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                productService.create(product)
        );
        //Then
        assertEquals("Barcode cannot be null", exception.getMessage());
    }

    @Test
    public void ensureProductBarcodeNotBeEmpty(){
        Product product = new Product();
        product.setBarcode("");
        product.setDescription("Tshirt");
        //product.setMobileSalesPrice(5.5);
        //product.setSalesPrice(5);
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                productService.create(product)
        );
        //Then
        assertEquals("Barcode cannot be empty", exception.getMessage());
    }

    @Test
    public void changeWebSalesPrice(){
        double oldPrice=5.5;
        double newPrice=5;
        String barcode="BR02";
        productService.deleteByBarcode(barcode);

        Product product = new Product();
        product.setImageUrl("imageURL:BR02");
        product.setTitle("Polo Yaka Tshirt");
        product.setBarcode(barcode);
        product.setDescription("Tshirt");
        product.setPrice(newPrice);
        product.setOldPrice(oldPrice);
        productService.create(product);

        productService.changePrice(product.getId(),newPrice);
        Product product1=productService.findByBarcode(barcode);
        assertEquals(product1.getPrice(),newPrice);
    }
    @Test
    public void changePriceBR02(){
        Product product=productService.findByBarcode("BR02");
        productService.changePrice(product.getId(),8);
    }
    @Test
    public void changePriceBR01(){
        Product product=productService.findByBarcode("BR01");
        productService.changePrice(product.getId(),7);
    }
    @Test
    public void changeStockBR02(){
        Product product=productService.findByBarcode("BR02");
        productService.changeStock(product.getId(),70);
    }
    @Test
    public void changeStockBR01(){
        Product product=productService.findByBarcode("BR01");
        productService.changeStock(product.getId(),7);
    }
}
