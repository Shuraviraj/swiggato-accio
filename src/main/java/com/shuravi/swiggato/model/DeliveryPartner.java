package com.shuravi.swiggato.model;

import com.shuravi.swiggato.enums.Gender;
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
@Table(name = "delivery_partner")
public class DeliveryPartner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    int id;

    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 40, message = "{validation.name.size.too_long}")
    @Column(name = "name", nullable = false)
    String name;

    @Size(min = 10, max = 10)
    @Column(name = "mobile_no", nullable = false, unique = true)
    String mobileNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    Gender gender;

    @OneToMany(mappedBy = "deliveryPartner", cascade = CascadeType.ALL)
    List<OrderEntity> orders = new ArrayList<>();

}
