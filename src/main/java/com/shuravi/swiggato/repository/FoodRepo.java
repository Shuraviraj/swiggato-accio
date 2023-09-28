package com.shuravi.swiggato.repository;

import com.shuravi.swiggato.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepo extends JpaRepository<FoodItem, Integer> {
}
