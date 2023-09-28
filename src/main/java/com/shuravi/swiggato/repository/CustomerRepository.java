package com.shuravi.swiggato.repository;

import com.shuravi.swiggato.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("select c from Customer c where c.mobileNo = ?1")
    Optional<Customer> findByMobileNo(String mobile);
}
