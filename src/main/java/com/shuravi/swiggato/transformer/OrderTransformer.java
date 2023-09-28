package com.shuravi.swiggato.transformer;

import com.shuravi.swiggato.dto.response.FoodResponse;
import com.shuravi.swiggato.dto.response.OrderResponse;
import com.shuravi.swiggato.model.Cart;
import com.shuravi.swiggato.model.FoodItem;
import com.shuravi.swiggato.model.OrderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTransformer {
    public static OrderEntity prepareOrderEntity(Cart cart) {
        return OrderEntity.builder()
                .orderId(String.valueOf(UUID.randomUUID()))
                .orderTotal(cart.getCartTotal())
                .build();
    }

    public static OrderResponse OrderToOrderResponse(OrderEntity savedOrder) {

        List<FoodResponse> foodResponseList = new ArrayList<>();
        for (FoodItem food : savedOrder.getFoodItems()) {
            FoodResponse foodResponse = FoodResponse.builder()
                    .dishName(food.getMenuItem().getDishName())
                    .price(food.getMenuItem().getPrice())
                    .category(food.getMenuItem().getCategory())
                    .veg(food.getMenuItem().isVeg())
                    .quantityAdded(food.getRequiredQuantity())
                    .build();

            foodResponseList.add(foodResponse);
        }

        return OrderResponse.builder()
                .orderId(savedOrder.getOrderId())
                .orderTime(savedOrder.getOrderTime())
                .orderTotal(savedOrder.getOrderTotal())
                .customerName(savedOrder.getCustomer().getName())
                .customerMobile(savedOrder.getCustomer().getMobileNo())
                .deliveryPartnerName(savedOrder.getDeliveryPartner().getName())
                .deliveryPartnerMobile(savedOrder.getDeliveryPartner().getMobileNo())
                .restaurantName(savedOrder.getRestaurant().getName())
                .foodResponses(foodResponseList)
                .build();
    }
}
