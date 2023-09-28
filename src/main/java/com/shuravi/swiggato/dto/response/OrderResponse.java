package com.shuravi.swiggato.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    String orderId;  // UUID

    double orderTotal;

    Date orderTime;

    String customerName;

    String customerMobile;

    String deliveryPartnerName;

    String deliveryPartnerMobile;

    String restaurantName;

    List<FoodResponse> foodResponses;
}
