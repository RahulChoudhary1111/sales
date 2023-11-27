package com.sales.admin.controllers;

import com.sales.dto.ItemDto;
import com.sales.dto.SearchFilters;
import com.sales.entities.Item;
import com.sales.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = {"item", "items"})
public class ItemController extends ServiceContainer {
    @GetMapping("/all")
    public ResponseEntity<Page<Item>> getAllItem(@RequestBody SearchFilters searchFilters) {
        Page<Item> alItems = itemService.getAllItems(searchFilters);
        return new ResponseEntity<>(alItems, HttpStatus.OK);
    }

    @GetMapping("/detail/{slug}")
    public ResponseEntity<Map<String, Object>> getItem(@PathVariable String slug) {
        Map<String, Object> responseObj = new HashMap<>();
        Item alItems = itemService.findItemBySLug(slug);
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


    @PostMapping(value = {"/add","/update"})
    public ResponseEntity<Map<String,Object>> addOrUpdateItems(HttpServletRequest request, @RequestBody ItemDto itemDto) {
        Map responseObj = new HashMap();
        User logggedUser = (User) request.getAttribute("user");
        responseObj = itemService.createOrUpdateItem(itemDto,logggedUser);
        return new ResponseEntity<>(responseObj,HttpStatus.valueOf((Integer) responseObj.get("status")));
    }


    @GetMapping("/delete/{slug}")
    public ResponseEntity<Map<String,Object>> deleteItemBySlug(@PathVariable String slug) {
        Map responseObj = new HashMap();
        int isUpdated = itemService.deleteItem(slug);
        if (isUpdated > 0) {
            responseObj.put("message", "Item has been successfully deleted.");
            responseObj.put("status", 200);
        }else{
            responseObj.put("message", "There is nothing to delete.recheck you parameters");
            responseObj.put("status", 400);
        }
        return new ResponseEntity<>(responseObj,HttpStatus.valueOf((Integer) responseObj.get("status")));
    }


    @GetMapping("/stock")
    public ResponseEntity<Map<String,Object>> stockSlug (@RequestBody Map<String,String> params) {
        Map responseObj = new HashMap();
        int isUpdated = itemService.updateStock(params.get("slug"),params.get("stock"));
        if (isUpdated > 0) {
            responseObj.put("message", "Item's stock has been successfully updated.");
            responseObj.put("status", 200);
        }else{
            responseObj.put("message", "There is nothing to delete.recheck you parameters");
            responseObj.put("status", 400);
        }
        return new ResponseEntity<>(responseObj,HttpStatus.valueOf((Integer) responseObj.get("status")));
    }

}
