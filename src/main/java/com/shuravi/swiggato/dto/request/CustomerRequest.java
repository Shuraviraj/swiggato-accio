package com.shuravi.swiggato.dto.request;

import com.shuravi.swiggato.enums.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    String name;

    String email;

    String address;

    String mobileNo;

    Gender gender;

}
