package com.shuravi.swiggato.controller;

import com.shuravi.swiggato.dto.request.DeliveryPartnerRequest;
import com.shuravi.swiggato.service.DeliveryPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partner")
public class DeliveryPartnerController {

    final DeliveryPartnerService deliveryPartnerService;

    @Autowired
    public DeliveryPartnerController(DeliveryPartnerService deliveryPartnerService) {
        this.deliveryPartnerService = deliveryPartnerService;
    }

    @PostMapping("/add")

    public ResponseEntity addDeliveryPartner(@RequestBody DeliveryPartnerRequest deliveryPartnerRequest) {
        var msg = deliveryPartnerService.addPartner(deliveryPartnerRequest);
        return new ResponseEntity(msg, HttpStatus.CREATED);
    }


}
