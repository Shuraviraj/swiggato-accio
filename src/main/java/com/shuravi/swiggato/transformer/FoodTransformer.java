package com.shuravi.swiggato.transformer;

import com.shuravi.swiggato.dto.response.FoodResponse;
import com.shuravi.swiggato.model.FoodItem;

public class FoodTransformer {
    public static FoodResponse FoodToFoodResponse(FoodItem food) {
        return FoodResponse.builder()
                .dishName(food.getMenuItem().getDishName())
                .price(food.getMenuItem().getPrice())
                .category(food.getMenuItem().getCategory())
                .veg(food.getMenuItem().isVeg())
                .quantityAdded(food.getRequiredQuantity())
                .build();
    }
}
