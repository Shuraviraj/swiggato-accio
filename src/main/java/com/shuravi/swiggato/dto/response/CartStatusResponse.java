package com.shuravi.swiggato.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartStatusResponse {
    String customerName;
    String customerAddress;
    String customerMobile;
    double cartTotal;
    List<FoodResponse> foodList;
    String restaurantName;
}
