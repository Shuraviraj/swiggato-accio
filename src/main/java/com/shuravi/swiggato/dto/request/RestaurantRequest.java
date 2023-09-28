package com.shuravi.swiggato.dto.request;

import com.shuravi.swiggato.enums.RestaurantCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequest {
    String name;

    String location;

    RestaurantCategory restaurantCategory;

    String contactNumber;
}
