package com.trendyol.product.service;


import com.trendyol.product.entity.Product;
import com.trendyol.product.kafkamodel.PriceChangeModel;
import com.trendyol.product.kafkamodel.StockChangeModel;
import com.trendyol.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final KafkaService kafkaService;

    public ProductService(ProductRepository productRepository, KafkaService kafkaService) {
        this.productRepository = productRepository;
        this.kafkaService = kafkaService;
    }

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public Product findById(String productId) {
        return this.productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Not found Product"));
    }
    public Product findByBarcode(String barcode) {
        return this.productRepository.findByBarcode(barcode);
    }
    public Product create(Product product) {
        if (product.getBarcode() == null) {
            throw new RuntimeException(String.format("Barcode cannot be null"));
        }
        if (product.getBarcode().isEmpty()) {
            throw new RuntimeException(String.format("Barcode cannot be empty"));
        }
        if (productRepository.existsByBarcode(product.getBarcode())) {
            throw new RuntimeException(String.format("There is already a product barcode as: %s", product.getBarcode()));
        }
        return this.productRepository.save(product);
    }


    public void changePrice(String productId, double price) {
        Product product = this.findById(productId);
        double oldPrice=product.getOldPrice();
        double newPrice=price;
        product.setPrice(price);
        productRepository.save(product);
        PriceChangeModel changeModel=new PriceChangeModel(productId,"web",oldPrice,price);
        kafkaService.sendChangePriceMessage(changeModel, "changeSalesPrice");
    }

    public void deleteById(String id) {
        productRepository.deleteById(id);
        kafkaService.sendMessage(id, "deleteProduct");
    }

    public void deleteByBarcode(String barcode) {
        boolean existsByBarcode = productRepository.existsByBarcode(barcode);

        if(!existsByBarcode)
            return;
        Product product=productRepository.findByBarcode(barcode);
        productRepository.deleteByBarcode(barcode);
        kafkaService.sendMessage(product.getId(), "deleteProduct");
    }
    public void changeStock(String productId,int quantity){
        Product product=findById(productId);
        int oldQuantity=product.getQuantity();
        product.setQuantity(quantity);
        kafkaService.sendChangeStockChangeMessage(new StockChangeModel(productId,oldQuantity,quantity), "changeProductStock");

    }
}