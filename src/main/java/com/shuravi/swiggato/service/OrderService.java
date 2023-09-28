package com.shuravi.swiggato.service;

import com.shuravi.swiggato.dto.response.OrderResponse;
import com.shuravi.swiggato.exception.CustomerNotFoundException;
import com.shuravi.swiggato.exception.EmptyCartException;
import com.shuravi.swiggato.model.Cart;
import com.shuravi.swiggato.model.DeliveryPartner;
import com.shuravi.swiggato.model.FoodItem;
import com.shuravi.swiggato.model.OrderEntity;
import com.shuravi.swiggato.model.OrderEntityRepository;
import com.shuravi.swiggato.model.Restaurant;
import com.shuravi.swiggato.repository.CustomerRepository;
import com.shuravi.swiggato.repository.DeliveryPartnerRepository;
import com.shuravi.swiggato.repository.RestaurantRepository;
import com.shuravi.swiggato.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {

    final CustomerRepository customerRepository;
    final DeliveryPartnerRepository deliveryPartnerRepository;
    private final OrderEntityRepository orderEntityRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public OrderService(CustomerRepository customerRepository, DeliveryPartnerRepository deliveryPartnerRepository,
                        OrderEntityRepository orderEntityRepository,
                        RestaurantRepository restaurantRepository) {
        this.customerRepository = customerRepository;
        this.deliveryPartnerRepository = deliveryPartnerRepository;
        this.orderEntityRepository = orderEntityRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public OrderResponse placeOrder(String mobile) {
        var customer = customerRepository
                .findByMobileNo(mobile)
                .orElseThrow(() -> new CustomerNotFoundException("Invalid Mobile Number"));


        // verify if cart is empty or not
        Cart cart = customer.getCart();
        if (cart.getFoodItems().size() == 0) {
            throw new EmptyCartException("Sorry! Your cart is empty!!!");
        }

        // find a delivery partner to deliver. Randomly
        DeliveryPartner partner = deliveryPartnerRepository.findRandomDeliveryPartner();
        Restaurant restaurant = cart.getFoodItems().get(0).getMenuItem().getRestaurant();

        // prepare the order entity
        OrderEntity order = OrderTransformer.prepareOrderEntity(cart);

        OrderEntity savedOrder = orderEntityRepository.save(order);

        order.setCustomer(customer);
        order.setDeliveryPartner(partner);
        order.setRestaurant(restaurant);
        order.setFoodItems(cart.getFoodItems());

        customer.getOrderEntities().add(savedOrder);
        partner.getOrders().add(savedOrder);
        restaurant.getOrders().add(savedOrder);

        for (FoodItem foodItem : cart.getFoodItems()) {
            foodItem.setCart(null);
            foodItem.setOrder(savedOrder);
        }
        clearCart(cart);

        customerRepository.save(customer);
        deliveryPartnerRepository.save(partner);
        restaurantRepository.save(restaurant);

        // prepare orderresponse
        return OrderTransformer.OrderToOrderResponse(savedOrder);
    }

    private void clearCart(Cart cart) {
        cart.setCartTotal(0);
        cart.setFoodItems(new ArrayList<>());
    }
}
