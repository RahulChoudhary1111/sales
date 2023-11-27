package com.sales.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreDto extends AddressDto {
    private String email;
    private Integer userId;
    private String storeName;
    private Float rating = (float) 0;
    private String status;
    private String phone="";
    private String slug;
    private String description ="";
}
