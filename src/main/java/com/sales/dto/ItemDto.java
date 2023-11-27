package com.sales.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private String name;
    private int WholesaleId;
    private float price;
    private float discount;
    private float rating;
    private String description;
    private String InStock;
    private  String slug;
}
