package com.shuravi.swiggato.service;

import com.shuravi.swiggato.dto.request.CustomerRequest;
import com.shuravi.swiggato.dto.response.CustomerResponse;

public interface CustomerService {
    CustomerResponse addCustomer(CustomerRequest customerRequest);

    CustomerResponse getCustomerByMobile(String mobile);
}
