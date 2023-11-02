package com.sales.services;


import com.sales.dto.PaginationDto;
import com.sales.dto.UserDto;
import com.sales.entities.User;
import com.sales.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService extends RepoContainer {




    public User findByEmailAndPassword(UserDto userDto) {
        return userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
    }

    public Page<User> getAllUser(PaginationDto paginationDto) {
        Sort sort = Sort.by(paginationDto.getAsc()).ascending();
        Pageable pageable = PageRequest.of(paginationDto.getPage(), paginationDto.getSize(), sort);
        return userRepository.findAll(pageable);

    }

    public Map<String, Object> createOrUpdateUser(UserDto userDto, User loggedUser) {
        Map<String, Object> responseObj = new HashMap<>();
        if (Utils.isEmpty(userDto.getSlug())) {
            int isUpdated = updateUser(userDto, loggedUser);
            if (isUpdated > 0) {
                responseObj.put("message", "successfully updated.");
                responseObj.put("status", 201);
            } else {
                responseObj.put("message", "nothing to updated. may be something went wrong");
                responseObj.put("status", 400);
            }
            return responseObj;
        } else {
            User updatedUser = createUser(userDto, loggedUser);
            if (updatedUser.getId() > 0) {
                responseObj.put("res", updatedUser);
                responseObj.put("message", "successfully inserted.");
                responseObj.put("status", 200);
            } else {
                responseObj.put("message", "nothing to insert. may be something went wrong");
                responseObj.put("status", 400);
            }
        }
        return responseObj;
    }

    public User createUser(UserDto userDto, User loggedUser) {
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

    public int updateUser(UserDto userDto, User loggedUser) {
        return userHbRepository.updateUser(userDto, loggedUser);
    }

    public User getUserDetail(String slug){
        return userRepository.findUserBySlug(slug);
    }

    public int deleteUserBySlug(String slug){
        return userHbRepository.deleteUserBySlug(slug);
    }

}
