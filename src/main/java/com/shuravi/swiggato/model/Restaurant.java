package com.shuravi.swiggato.model;

import com.shuravi.swiggato.enums.RestaurantCategory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    int id;

    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 40, message = "{validation.name.size.too_long}")
    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "location", nullable = false)
    String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "restaurant_category", nullable = false)
    RestaurantCategory restaurantCategory;

    @Size(min = 10, max = 10)
    @Column(name = "contact_number", nullable = false, unique = true)
    String contactNumber;

    @Column(name = "opened")
    boolean opened = false;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<MenuItem> availableMenuItems = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<OrderEntity> orders = new ArrayList<>();

}
