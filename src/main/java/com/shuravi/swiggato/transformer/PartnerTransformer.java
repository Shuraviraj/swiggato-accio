package com.shuravi.swiggato.transformer;

import com.shuravi.swiggato.dto.request.DeliveryPartnerRequest;
import com.shuravi.swiggato.model.DeliveryPartner;

import java.util.ArrayList;

public class PartnerTransformer {
    public static DeliveryPartner PartnerRequestToDeliveryPartner(
            DeliveryPartnerRequest deliveryPartnerRequest) {

        return DeliveryPartner.builder()
                .name(deliveryPartnerRequest.getName())
                .mobileNo(deliveryPartnerRequest.getMobileNo())
                .gender(deliveryPartnerRequest.getGender())
                .orders(new ArrayList<>())
                .build();
    }

}
