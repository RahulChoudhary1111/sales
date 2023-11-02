package com.sales.controllers;

import com.sales.entities.Store;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController extends ServiceContainer{

    @GetMapping("/store/all")
    public ResponseEntity<Page<Store>> getAllStore(){
        Page<Store> storePage =  storeService.getAllStore();
        return new ResponseEntity<>(storePage, HttpStatus.OK);
    }

}
