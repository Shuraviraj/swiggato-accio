package com.shuravi.swiggato.service.impl;

import com.shuravi.swiggato.dto.request.FoodRequest;
import com.shuravi.swiggato.dto.response.CartStatusResponse;
import com.shuravi.swiggato.dto.response.FoodResponse;
import com.shuravi.swiggato.exception.CustomerNotFoundException;
import com.shuravi.swiggato.exception.MenuItemNotFoundException;
import com.shuravi.swiggato.model.Cart;
import com.shuravi.swiggato.model.Customer;
import com.shuravi.swiggato.model.FoodItem;
import com.shuravi.swiggato.model.MenuItem;
import com.shuravi.swiggato.repository.CartRepository;
import com.shuravi.swiggato.repository.CustomerRepository;
import com.shuravi.swiggato.repository.FoodRepo;
import com.shuravi.swiggato.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    final CustomerRepository customerRepository;
    final MenuRepository menuRepository;
    final CartRepository cartRepository;
    final FoodRepo foodRepo;

    @Autowired
    public CartService(CustomerRepository customerRepository, MenuRepository menuRepository, CartRepository cartRepository, FoodRepo foodRepo) {
        this.customerRepository = customerRepository;
        this.menuRepository = menuRepository;
        this.cartRepository = cartRepository;
        this.foodRepo = foodRepo;
    }

    public CartStatusResponse addFoodItemToCart(FoodRequest foodRequest) {
        Customer customer = customerRepository.findByMobileNo(foodRequest.getCustomerMobile())
                .orElseThrow(() -> new CustomerNotFoundException("Customer doesn't exist"));

        Optional<MenuItem> menuItemOptional = menuRepository.findById(foodRequest.getMenuItemId());
        if (menuItemOptional.isEmpty()) {
            throw new MenuItemNotFoundException("Item not available in the restaurant!!!");
        }

        MenuItem menuItem = menuItemOptional.get();
        if (!menuItem.getRestaurant().isOpened() || !menuItem.isAvailable()) {
            throw new MenuItemNotFoundException("Given dish is out of stock for now!!!");
        }

        // ready to add item to cart
        FoodItem foodItem = FoodItem.builder()
                .menuItem(menuItem)
                .requiredQuantity(foodRequest.getRequiredQuantity())
                .totalCost((double) foodRequest.getRequiredQuantity() * menuItem.getPrice())
                .build();

        Cart cart = customer.getCart();
        FoodItem savedFoodItem = foodRepo.save(foodItem);

        double cartTotal = 0;
        cart.getFoodItems().add(savedFoodItem);
        for (FoodItem food : cart.getFoodItems()) {
            cartTotal += food.getRequiredQuantity() * food.getMenuItem().getPrice();
        }
        savedFoodItem.setCart(cart);
        cart.setCartTotal(cartTotal);
        menuItem.getFoodItems().add(savedFoodItem);

        // save
        Cart savedCart = cartRepository.save(cart);
        MenuItem savedMenuItem = menuRepository.save(menuItem);

        List<FoodResponse> foodResponseList = new ArrayList<>();
        for (FoodItem food : cart.getFoodItems()) {
            FoodResponse foodResponse = FoodResponse.builder()
                    .dishName(food.getMenuItem().getDishName())
                    .price(food.getMenuItem().getPrice())
                    .category(food.getMenuItem().getCategory())
                    .veg(food.getMenuItem().isVeg())
                    .quantityAdded(food.getRequiredQuantity())
                    .build();

            foodResponseList.add(foodResponse);
        }

        return CartStatusResponse.builder()
                .customerName(savedCart.getCustomer().getName())
                .customerMobile(savedCart.getCustomer().getMobileNo())
                .customerAddress(savedCart.getCustomer().getAddress())
                .foodList(foodResponseList)
                .restaurantName(savedMenuItem.getRestaurant().getName())
                .cartTotal(cartTotal)
                .build();
        //------------------------------------------------------------------
    }

//    public CartStatusResponse addFoodItemToCart(FoodRequest foodRequest) {
//        Customer customer = customerRepository.findByMobileNo(foodRequest.getCustomerMobile())
//                .orElseThrow(() -> new CustomerNotFoundException("Customer doesn't exist"));
//
//        Optional<MenuItem> menuItemOptional = menuRepository.findById(foodRequest.getMenuItemId());
//        if (menuItemOptional.isEmpty()) {
//            throw new MenuItemNotFoundException("Item not available in the restaurant!!!");
//        }
//
//        MenuItem menuItem = menuItemOptional.get();
//        if (!menuItem.getRestaurant().isOpened() || !menuItem.isAvailable()) {
//            throw new MenuItemNotFoundException("Given dish is out of stock for now!!!");
//        }
//
//        // ready to add item to cart
//        FoodItem foodItem = FoodItem.builder()
//                .menuItem(menuItem)
//                .cart(customer.getCart())
//                .requiredQuantity(foodRequest.getRequiredQuantity())
//                .totalCost((double) foodRequest.getRequiredQuantity() * menuItem.getPrice())
//                .build();
//
//        Cart cart = customer.getCart();
//        FoodItem savedFoodItem = foodRepo.save(foodItem);
//
//        double cartTotal = 0;
//        cart.getFoodItems().add(savedFoodItem);
//        for (FoodItem food : cart.getFoodItems()) {
//            cartTotal += food.getRequiredQuantity() * food.getMenuItem().getPrice();
//        }
//
//        savedFoodItem.setCart(cart);
//        cart.setCartTotal(cartTotal);
//        menuItem.getFoodItems().add(savedFoodItem);
//
//        // save
//        Cart savedCart = cartRepository.save(cart);
//        MenuItem savedMenuItem = menuRepository.save(menuItem);
//
//        // prepare
//        List<FoodResponse> foodResponseList = new ArrayList<>();
//        for (FoodItem food : cart.getFoodItems()) {
//            FoodResponse foodResponse = FoodResponse.builder()
//                    .dishName(food.getMenuItem().getDishName())
//                    .price(food.getMenuItem().getPrice())
//                    .category(food.getMenuItem().getCategory())
//                    .veg(food.getMenuItem().isVeg())
//                    .quantityAdded(food.getRequiredQuantity())
//                    .build();
//
//            foodResponseList.add(foodResponse);
//        }
//
//        return CartStatusResponse.builder()
//                .customerName(savedCart.getCustomer().getName())
//                .customerMobile(savedCart.getCustomer().getMobileNo())
//                .customerAddress(savedCart.getCustomer().getAddress())
//                .foodList(foodResponseList)
//                .restaurantName(savedMenuItem.getRestaurant().getName())
//                .cartTotal(cartTotal)
//                .build();
//    }
}
