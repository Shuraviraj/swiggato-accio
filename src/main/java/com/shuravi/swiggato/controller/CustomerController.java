package com.shuravi.swiggato.controller;

import com.shuravi.swiggato.dto.request.CustomerRequest;
import com.shuravi.swiggato.dto.response.CustomerResponse;
import com.shuravi.swiggato.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

//    //    @Autowired
////    CustomerService customerService;// attribute/ field injection
//    private final CustomerService customerService;
//
//    @Autowired
//    public CustomerController(CustomerService customerService) { // constructor injection
//        this.customerService = customerService;
//    }

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.addCustomer(customerRequest);
        return new ResponseEntity(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping("/find/mobile/{mobile}")
    public ResponseEntity getCustomerByMobile(@PathVariable("mobile") String mobile) {
        try {
            CustomerResponse customerResponse = customerService.getCustomerByMobile(mobile);
            return new ResponseEntity(customerResponse, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
