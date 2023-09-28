package com.shuravi.swiggato.controller;

import com.shuravi.swiggato.dto.request.MenuRequest;
import com.shuravi.swiggato.dto.request.RestaurantRequest;
import com.shuravi.swiggato.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/add")
    public ResponseEntity addRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        var restaurant = restaurantService.addRestaurant(restaurantRequest);
        return new ResponseEntity(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/update/status")
    public ResponseEntity changeOpenedStatus(@RequestParam int id) {
        var response = restaurantService.changeOpenedStatus(id);
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add/menu")
    public ResponseEntity addMenuItemToRestaurant(@RequestBody MenuRequest menuRequest) {
        var response = restaurantService.addMenuItemToRestaurant(menuRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
