package com.sales.admin.controllers;

import com.sales.dto.SearchFilters;
import com.sales.dto.StoreDto;
import com.sales.entities.Store;
import com.sales.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin/store")
public class StoreController extends ServiceContainer{

    @PostMapping("/all")
    public ResponseEntity<Page<Store>> getAllStore(@RequestBody SearchFilters searchFilters){
        Page<Store> storePage =  storeService.getAllStore(searchFilters);
        return new ResponseEntity<>(storePage, HttpStatus.OK);
    }

    @GetMapping("/delete/{slug}")
    public ResponseEntity<Map<String,Object>> deleteStore(@PathVariable String slug) {
        Map responseObj = new HashMap();
        int isUpdated = storeService.deleteStoreBySlug(slug);
        if (isUpdated > 0) {
            responseObj.put("message", "Store has been successfully deleted.");
            responseObj.put("status", 200);
        }else{
            responseObj.put("message", "There is nothing to delete.recheck you parameters");
            responseObj.put("status", 400);
        }
        return new ResponseEntity<>(responseObj,HttpStatus.valueOf((Integer) responseObj.get("status")));
    }

    @PostMapping(value = {"/add","/update"})
    public ResponseEntity<Map<String,Object>> addStoreOrUpdateStore(HttpServletRequest request, @RequestBody StoreDto storeDto) {
        Map responseObj = new HashMap();
        User logggedUser = (User) request.getAttribute("user");
        responseObj = storeService.createOrUpdateStore(storeDto,logggedUser);
        return new ResponseEntity<>(responseObj,HttpStatus.valueOf((Integer) responseObj.get("status")));
    }

}
