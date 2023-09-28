package com.shuravi.swiggato.transformer;

import com.shuravi.swiggato.dto.request.CustomerRequest;
import com.shuravi.swiggato.dto.response.CartResponse;
import com.shuravi.swiggato.dto.response.CustomerResponse;
import com.shuravi.swiggato.model.Customer;

public class CustomerTransformer {
    public static Customer CustomerRequestToCustomer(CustomerRequest customerRequest) {
        return Customer.builder()
                .name(customerRequest.getName())
                .email(customerRequest.getEmail())
                .address(customerRequest.getAddress())
                .mobileNo(customerRequest.getMobileNo())
                .gender(customerRequest.getGender())
                .build();
    }

    public static CustomerResponse CustomerToCustomerResponse(Customer customer) {
        CartResponse cartResponse = CartTransformer.cartToCartResponse(customer.getCart());
        return CustomerResponse.builder()
                .name(customer.getName())
                .address(customer.getAddress())
                .mobileNo(customer.getMobileNo())
                .cart(cartResponse)
                .build();
    }

}
