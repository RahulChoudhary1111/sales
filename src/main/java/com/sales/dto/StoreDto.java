package com.sales.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreDto {
    private String email;
    private String storeName;
    private Float rating = (float) 0;
    private String status;
    private String phone="";
    private String slug;
    private String description ="";
    private Float latitude = (float) 0;
    private Float longitude =  (float) 0;
    private String address;
}
