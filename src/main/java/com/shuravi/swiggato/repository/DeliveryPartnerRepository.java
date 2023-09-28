package com.shuravi.swiggato.repository;

import com.shuravi.swiggato.model.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner, Integer> {

    String RANDOM_DELIVERY_PARTNER_QUERY = "select d from DeliveryPartner d order by rand() limit 1";

    @Query(value = RANDOM_DELIVERY_PARTNER_QUERY)
    DeliveryPartner findRandomDeliveryPartner();
}
