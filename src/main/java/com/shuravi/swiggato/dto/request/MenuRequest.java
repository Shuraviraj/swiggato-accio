package com.shuravi.swiggato.dto.request;

import com.shuravi.swiggato.enums.FoodCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuRequest {
    int restaurantId;

    String dishName;

    double price;

    FoodCategory category;

    boolean veg;

    boolean available;
}
