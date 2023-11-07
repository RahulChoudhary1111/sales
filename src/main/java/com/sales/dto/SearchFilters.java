package com.sales.dto;


import com.sales.utils.Utils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchFilters {

    String name = "";
    String status="A";
    Long fromDate=0L;
    Long toDate= Utils.getCurrentMillis();
    String orderBy="id";
    String order = "desc";
    int pageNumber = 0;
    int size = 10;
}
