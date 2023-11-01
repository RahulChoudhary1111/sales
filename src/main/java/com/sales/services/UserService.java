package com.sales.services;


import com.sales.dto.UserDto;
import com.sales.entities.User;
import com.sales.repositories.UserRepository;
import com.sales.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findByEmailAndPassword(UserDto userDto){
      return  userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
    }


    public User createUser(UserDto userDto,User loggedUser){
        HashMap responseObj = new HashMap();
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setSlug(UUID.randomUUID().toString());
        user.setPassword(userDto.getPassword());
        user.setStatus("A");
        user.setContact(userDto.getContact());
        user.setEmail(userDto.getEmail());
        user.setUserType(userDto.getUserType());
        user.setUpdatedAt(Utils.getCurrentMillis());
        user.setCreatedAt(Utils.getCurrentMillis());
        user.setCreatedBy(loggedUser.getId());
        user.setUpdatedBy(loggedUser.getId());
        return userRepository.save(user);
    }




}
