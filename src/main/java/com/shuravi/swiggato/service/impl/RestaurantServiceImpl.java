package com.shuravi.swiggato.service.impl;

import com.shuravi.swiggato.dto.request.MenuRequest;
import com.shuravi.swiggato.dto.request.RestaurantRequest;
import com.shuravi.swiggato.dto.response.RestaurantResponse;
import com.shuravi.swiggato.exception.RestaurantNotFoundException;
import com.shuravi.swiggato.model.MenuItem;
import com.shuravi.swiggato.model.Restaurant;
import com.shuravi.swiggato.repository.RestaurantRepository;
import com.shuravi.swiggato.service.RestaurantService;
import com.shuravi.swiggato.transformer.MenuItemTransformer;
import com.shuravi.swiggato.transformer.RestaurantTransformer;
import com.shuravi.swiggato.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ValidationUtils validationUtils;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ValidationUtils validationUtils) {
        this.restaurantRepository = restaurantRepository;
        this.validationUtils = validationUtils;
    }

    @Override
    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest) {
        var restaurant = RestaurantTransformer.RestaurantRequestToRestaurant(restaurantRequest);

        var savedRestaurant = restaurantRepository.save(restaurant);

        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);
    }

    @Override
    public String changeOpenedStatus(int id) {
        //check whether id is valid or not
        if (!validationUtils.validateRestaurantId(id)) {
            throw new RestaurantNotFoundException("Restaurant doesn't exist!!");
        }

        Restaurant restaurant = restaurantRepository.findById(id).get();
        restaurant.setOpened(!restaurant.isOpened());
        restaurantRepository.save(restaurant);

        if (restaurant.isOpened()) {
            return "Restaurant is opened now!!!";
        }

        return "Restaurant is closed";
    }

    @Override
    public RestaurantResponse addMenuItemToRestaurant(MenuRequest menuRequest) {
        // check reataurant is valid or not
        if (!validationUtils.validateRestaurantId(menuRequest.getRestaurantId())) {
            throw new RestaurantNotFoundException("Restaurant doesn't exist!!");
        }

        Restaurant restaurant = restaurantRepository.findById(menuRequest.getRestaurantId()).get();
        // make food entity
        MenuItem menuItem = MenuItemTransformer.MenuRequestToMenuItem(menuRequest);
        menuItem.setRestaurant(restaurant);

        restaurant.getAvailableMenuItems().add(menuItem);

        // save rest and food
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        // prepare response
        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);
    }
}
