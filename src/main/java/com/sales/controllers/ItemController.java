package com.sales.controllers;

import com.sales.dto.ItemDto;
import com.sales.dto.PaginationDto;
import com.sales.entities.Item;
import com.sales.entities.Store;
import com.sales.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ItemController extends ServiceContainer {
    @GetMapping("/item/all")
    public ResponseEntity<Page<Item>> getAllItem() {
        Page<Item> alItems = itemService.getAllItems();
        //Page<Item> allItems =  itemService.getAllItems(paginationDto);
        return new ResponseEntity<>(alItems, HttpStatus.OK);
    }

    @GetMapping("/item/slug/{slug}")
    public ResponseEntity<Map<String, Object>> getItem(@PathVariable String slug) {
        Map<String, Object> responseObj = new HashMap<>();
        Item alItems = itemService.getItemBySlug(slug);
        if (alItems != null) {
            responseObj.put("message", "success");
            responseObj.put("res", alItems);
            responseObj.put("status", 200);
        } else {
            responseObj.put("message", "Item Not Found");
            responseObj.put("status", 404);
        }
        return new ResponseEntity<>(responseObj, HttpStatus.valueOf((Integer) responseObj.get("status")));
    }
}
