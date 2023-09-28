package com.shuravi.swiggato.service;

import com.shuravi.swiggato.dto.request.DeliveryPartnerRequest;
import com.shuravi.swiggato.repository.DeliveryPartnerRepository;
import com.shuravi.swiggato.transformer.PartnerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryPartnerService {

    final DeliveryPartnerRepository deliveryPartnerRepository;

    @Autowired
    public DeliveryPartnerService(DeliveryPartnerRepository deliveryPartnerRepository) {
        this.deliveryPartnerRepository = deliveryPartnerRepository;
    }

    public String addPartner(DeliveryPartnerRequest deliveryPartnerRequest) {
        //dto -> entity
        var deliveryPartner = PartnerTransformer.PartnerRequestToDeliveryPartner(deliveryPartnerRequest);

        //save
        deliveryPartnerRepository.save(deliveryPartner);
     
        //return
        return "Delivery Partner added successfully";
    }
}
