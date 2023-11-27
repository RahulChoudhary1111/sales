package com.sales.admin.controllers;


import com.sales.dto.UserDto;
import com.sales.dto.UserSearchFilters;
import com.sales.entities.User;
import com.sales.jwtUtils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController extends ServiceContainer {

    @Autowired
    JwtToken jwtToken;

    @PostMapping("/auth/all")
    public ResponseEntity<Page<User>> getAllUsers(@RequestBody UserSearchFilters searchFilters){
        Page<User> userPage =  userService.getAllUser(searchFilters);
        return new ResponseEntity<>(userPage,HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> findByEmailAndPassword(@RequestBody UserDto userDetails){
        Map<String,Object> responseObj = new HashMap<>();
        User user = userService.findByEmailAndPassword(userDetails);
        if(user !=null){
            userDetails.setToken("Bearer "+jwtToken.generateToken(user));
            responseObj.put("message","success");
            responseObj.put("status" , 200);
            responseObj.put("res",userDetails);
            return new ResponseEntity<>(responseObj, HttpStatus.OK);
        }else{
            responseObj.put("message","invalid credentials");
            responseObj.put("status" , 401);
            return new ResponseEntity<>(responseObj, HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping(value = {"/auth/add","/auth/update"})
    public ResponseEntity<Map<String,Object>>  register(HttpServletRequest request, @RequestBody  UserDto userDto) {
        Map responseObj = new HashMap();
        User logggedUser = (User) request.getAttribute("user");
        responseObj = userService.createOrUpdateUser(userDto,logggedUser);
        return new ResponseEntity<>(responseObj,HttpStatus.valueOf((Integer) responseObj.get("status")));
        }

    @GetMapping("/auth/detail/{slug}")
    public ResponseEntity<Map<String,Object>> getDetailUser(@PathVariable String slug) {
        Map responseObj = new HashMap();
        User user = userService.getUserDetail(slug);
        if (user!= null){
            responseObj.put("res", user);
            responseObj.put("status", 200);
        }else {
            responseObj.put("message", "Please check you parameters not a valid request.");
            responseObj.put("status", 400);
        }
        return new ResponseEntity<>(responseObj,HttpStatus.valueOf((Integer) responseObj.get("status")));
    }

    @GetMapping("/auth/delete/{slug}")
    public ResponseEntity<Map<String,Object>> deleteUserBySlug(@PathVariable String slug) {
        Map responseObj = new HashMap();
        int isUpdated = userService.deleteUserBySlug(slug);
        if (isUpdated > 0) {
            responseObj.put("message", "User has been successfully deleted.");
            responseObj.put("status", 200);
        }else{
            responseObj.put("message", "There is nothing to delete.recheck you parameters");
            responseObj.put("status", 400);
        }
        return new ResponseEntity<>(responseObj,HttpStatus.valueOf((Integer) responseObj.get("status")));
    }
}




