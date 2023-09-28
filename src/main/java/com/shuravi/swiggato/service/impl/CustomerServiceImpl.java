package com.shuravi.swiggato.service.impl;

import com.shuravi.swiggato.dto.request.CustomerRequest;
import com.shuravi.swiggato.dto.response.CustomerResponse;
import com.shuravi.swiggato.exception.CustomerNotFoundException;
import com.shuravi.swiggato.model.Cart;
import com.shuravi.swiggato.repository.CustomerRepository;
import com.shuravi.swiggato.service.CustomerService;
import com.shuravi.swiggato.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        var customer = CustomerTransformer.CustomerRequestToCustomer(customerRequest);

        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();

        customer.setCart(cart);
        var savedCustomer = customerRepository.save(customer);

        return CustomerTransformer.CustomerToCustomerResponse(savedCustomer);
    }

    @Override
    public CustomerResponse getCustomerByMobile(String mobile) {
        var customer = customerRepository.findByMobileNo(mobile);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException("Customer not found");
        }
        return CustomerTransformer.CustomerToCustomerResponse(customer.get());
    }
}
