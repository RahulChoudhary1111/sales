package com.sales.controllers;


import com.sales.dto.UserDto;
import com.sales.entities.User;
import com.sales.jwtUtils.JwtToken;
import com.sales.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {


    @Autowired
    UserService userService;
    @Autowired
    JwtToken jwtToken;

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

    /** create user*/

    @PostMapping(value = {"/auth/add","/auth/update"})
    public ResponseEntity<Map<String,Object>>  register(HttpServletRequest request, @RequestBody  UserDto userDto) {
        HashMap responseObj = new HashMap();
        User logggedUser = (User) request.getAttribute("user");
        User updatedUser = userService.createUser(userDto,logggedUser);
        if (updatedUser.getId() > 0){
            responseObj.put("message", "successfully updated." );
            responseObj.put("status" , 201);
            return new ResponseEntity<>(responseObj, HttpStatus.valueOf(201));
        }else {
            responseObj.put("message", "successfully inserted." );
            responseObj.put("status" , 200);
            return new ResponseEntity<>(responseObj,HttpStatus.valueOf(200));
        }
    }



}