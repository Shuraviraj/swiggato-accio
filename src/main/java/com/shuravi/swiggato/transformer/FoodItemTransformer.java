package com.shuravi.swiggato.transformer;

import com.shuravi.swiggato.dto.request.MenuRequest;
import com.shuravi.swiggato.dto.response.MenuResponse;
import com.shuravi.swiggato.model.MenuItem;

public class FoodItemTransformer {
    public static MenuItem FoodRequestToFoodItem(MenuRequest menuRequest) {
        return MenuItem.builder()
                .dishName(menuRequest.getDishName())
                .price(menuRequest.getPrice())
                .category(menuRequest.getCategory())
                .veg(menuRequest.isVeg())
                .available(menuRequest.isAvailable())
                .build();
    }

    public static MenuResponse FoodItemToFoodResponse(MenuItem menuItem) {
        return MenuResponse.builder()
                .dishName(menuItem.getDishName())
                .price(menuItem.getPrice())
                .veg(menuItem.isVeg())
                .foodCategory(menuItem.getCategory())
                .build();
    }
}
