package com.shuravi.swiggato.service;

import com.shuravi.swiggato.dto.request.MenuRequest;
import com.shuravi.swiggato.dto.request.RestaurantRequest;
import com.shuravi.swiggato.dto.response.RestaurantResponse;

public interface RestaurantService {
    RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest);

    String changeOpenedStatus(int id);

    RestaurantResponse addMenuItemToRestaurant(MenuRequest menuRequest);
}
