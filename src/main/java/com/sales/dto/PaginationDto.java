package com.sales.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaginationDto {

    int page = 0;
    int size = 10;
    String asc = "id";

}
