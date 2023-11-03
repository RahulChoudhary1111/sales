package com.sales.controllers;

import com.sales.dto.PaginationDto;
import com.sales.entities.Store;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController extends ServiceContainer{

    @GetMapping("/store/all")
    public ResponseEntity<Page<Store>> getAllStore(@RequestBody(required = false) PaginationDto paginationDto){
        Page<Store> storePage =  storeService.getAllStore(paginationDto);
        return new ResponseEntity<>(storePage, HttpStatus.OK);
    }

}
