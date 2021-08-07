package com.trendyol.basket.infrastructure.externalservice.product;

import com.trendyol.basket.application.externalservice.product.ProductService;
import com.trendyol.basket.application.externalservice.product.request.GetProductRequest;
import com.trendyol.basket.application.externalservice.product.response.GetProductResponse;
import com.trendyol.basket.application.model.dto.ProductInfoDTO;
import com.trendyol.basket.infrastructure.externalservice.product.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductServiceImpl implements ProductService {
    private final RestTemplate template;
    @Value("${productApiAddress}")
    private String productServiceUrl;

    public ProductServiceImpl(RestTemplate template) {
        this.template = template;
    }

    @Override
    public GetProductResponse get(GetProductRequest getProductRequest) {
        String url = productServiceUrl + "/product/findbyid?id=" + getProductRequest.getProductId();
        ResponseEntity<ProductInfoDTO> responseEntity = template.getForEntity(url, ProductInfoDTO.class);
        ProductInfoDTO product = responseEntity.getBody();
        return new GetProductResponse(product);
    }
}
