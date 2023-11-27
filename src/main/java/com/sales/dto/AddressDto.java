package com.sales.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

    String slug;
    String city;
    String state;
    Float latitude;
    Float altitude;

}
