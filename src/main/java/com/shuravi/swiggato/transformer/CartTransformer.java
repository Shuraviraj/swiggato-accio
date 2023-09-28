package com.shuravi.swiggato.transformer;

import com.shuravi.swiggato.dto.response.CartResponse;
import com.shuravi.swiggato.model.Cart;

import java.util.ArrayList;

public class CartTransformer {
    public static CartResponse cartToCartResponse(Cart cart) {

        return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .foodItems(new ArrayList<>())
                .build();

    }
}
