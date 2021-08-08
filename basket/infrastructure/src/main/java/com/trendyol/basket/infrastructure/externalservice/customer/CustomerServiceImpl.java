package com.trendyol.basket.infrastructure.externalservice.customer;

import com.trendyol.basket.application.externalservice.customer.CustomerService;
import com.trendyol.basket.application.externalservice.customer.response.GetCustomerResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class CustomerServiceImpl implements CustomerService {
    private final RestTemplate template;
    @Value("${customerApiAddress}")
    private String userServiceUrl;

    public CustomerServiceImpl(RestTemplate template) {
        this.template = template;
    }


    @Override
    public GetCustomerResponse get(long id) {
        String url=userServiceUrl+"/user/"+id;
        ResponseEntity<GetCustomerResponse> responseEntity=template.getForEntity(url,GetCustomerResponse.class);
        return responseEntity.getBody();
    }
}
