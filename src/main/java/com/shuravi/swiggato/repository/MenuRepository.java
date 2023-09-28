package com.shuravi.swiggato.repository;

import com.shuravi.swiggato.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem, Integer> {
}
