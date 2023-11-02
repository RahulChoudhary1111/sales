package com.sales.services;


import com.sales.dto.UserDto;
import com.sales.entities.User;
import com.sales.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService extends RepoContainer {


    public User findByEmailAndPassword(UserDto userDto){
      return  userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
    }


    public User createUser(UserDto userDto,User loggedUser){
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

    public int updateUser(UserDto userDto, User loggedUser){
        return userHbRepository.updateUser(userDto,loggedUser);
    }




}
