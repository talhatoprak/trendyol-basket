package com.trendyol.basket.application.externalservice.customer;

import com.trendyol.basket.application.externalservice.customer.response.GetCustomerResponse;

public interface CustomerService {
    GetCustomerResponse get(long id);
}
