package com.shuravi.swiggato.controller;

import com.shuravi.swiggato.dto.request.FoodRequest;
import com.shuravi.swiggato.dto.response.CartStatusResponse;
import com.shuravi.swiggato.service.impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

//    //    @Autowired
////    CustomerService customerService;// attribute/ field injection
//    private final CustomerService customerService;
//
//    @Autowired
//    public CustomerController(CustomerService customerService) { // constructor injection
//        this.customerService = customerService;
//    }

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity addFoodItemToCart(@RequestBody FoodRequest foodRequest) {
        try {
            CartStatusResponse cartStatusResponse = cartService.addFoodItemToCart(foodRequest);
            return new ResponseEntity(cartStatusResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

