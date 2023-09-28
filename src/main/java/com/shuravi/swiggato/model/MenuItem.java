package com.shuravi.swiggato.model;

import com.shuravi.swiggato.enums.FoodCategory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "menu_item")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    int id;

    @Column(name = "dish_name")
    String dishName;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    FoodCategory category;

    @Column(name = "price")
    double price;

    @Column(name = "veg")
    boolean veg;

    @Column(name = "available")
    boolean available;

    @ManyToOne
    @JoinColumn
    Restaurant restaurant;

    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL)
    List<FoodItem> foodItems = new ArrayList<>();

}
