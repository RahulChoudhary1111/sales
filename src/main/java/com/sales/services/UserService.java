package com.sales.services;


import com.sales.dto.UserDto;
import com.sales.dto.UserSearchFilters;
import com.sales.entities.User;
import com.sales.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.sales.specifications.UserSpecifications.*;

@Service
public class UserService extends RepoContainer {


    public User findByEmailAndPassword(UserDto userDto) {
        return userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
    }


    public Page<User> getAllUser(UserSearchFilters filters) {
        Specification<User> specification = Specification.where(
            containsName(filters.getName())
            .and(greaterThanOrEqualFromDate(filters.getFromDate()))
            .and(lessThanOrEqualToToDate(filters.getToDate()))
            .and(isStatus(filters.getStatus()))
            .and(hasUserType(filters.getUserType()))
        );
        Pageable pageable = getPageable(filters);
        return userRepository.findAll(specification,pageable);
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
        User user = new User(loggedUser);
        user.setUsername(userDto.getUsername());
        user.setSlug(UUID.randomUUID().toString());
        user.setPassword(userDto.getPassword());
        user.setContact(userDto.getContact());
        user.setEmail(userDto.getEmail());
        user.setUserType(userDto.getUserType());
        return userRepository.save(user);
    }

    public int updateUser(UserDto userDto, User loggedUser){
        return userHbRepository.updateUser(userDto,loggedUser);
    }

    public User getUserDetail(String slug){
        return userRepository.findUserBySlug(slug);
    }

    public int deleteUserBySlug(String slug){
        return userHbRepository.deleteUserBySlug(slug);
    }

}
