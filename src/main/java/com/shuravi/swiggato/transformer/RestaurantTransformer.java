package com.shuravi.swiggato.transformer;

import com.shuravi.swiggato.dto.request.RestaurantRequest;
import com.shuravi.swiggato.dto.response.MenuResponse;
import com.shuravi.swiggato.dto.response.RestaurantResponse;
import com.shuravi.swiggato.model.Restaurant;

import java.util.List;

public class RestaurantTransformer {
    public static Restaurant RestaurantRequestToRestaurant(RestaurantRequest restaurantRequest) {

        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequest.getName());
        restaurant.setLocation(restaurantRequest.getLocation());
        restaurant.setRestaurantCategory(restaurantRequest.getRestaurantCategory());
        restaurant.setContactNumber(restaurantRequest.getContactNumber());
        restaurant.setOpened(true);
        return restaurant;
    }

    public static RestaurantResponse RestaurantToRestaurantResponse(Restaurant restaurant) {
        List<MenuResponse> menu = restaurant.getAvailableMenuItems()
                .stream()
                .map(MenuItemTransformer::MenuItemToMenuResponse)
                .toList();

        var response = new RestaurantResponse();
        response.setName(restaurant.getName());
        response.setLocation(restaurant.getLocation());
        response.setContactNumber(restaurant.getContactNumber());
        response.setOpened(restaurant.isOpened());
        response.setMenu(menu);
        return response;
    }
}
