package com.trendyol.product.controller;

import com.trendyol.product.entity.Product;
import com.trendyol.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @GetMapping("/findbyid")
    public Product findById(@RequestParam String id){
        System.out.println(String.format("ProductController :: findById :: ProductId = %s",id));
        return this.productService.findById(id);
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product){
        System.out.println("ProductController :: createProduct :: "+product.toString());
        return this.productService.create(product);
    }

    @PostMapping("/delete")
    public  void deleteById(@RequestParam String id)
    {
        System.out.println(String.format("ProductController :: deleteById :: ProductId = %s",id));
        productService.deleteById(id);
    }

}
