package com.sales.repositories;


import com.sales.dto.UserDto;
import com.sales.entities.User;
import com.sales.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class UserHbRepository {

    @Autowired
    EntityManager entityManager;

    public User updateUser(UserDto userDto){
        String strQuery = "update user set " +
                "username=:username , " +
                "email=:email,"+
                "password=:password,"+
                "contact=:contact,"+
                "userType=:userType,"+
                "createAt=:createdAt,"+
                "updatedAt=:updatedAt,"+
                "updatedBy=:updatedBy,"+
                "createdBy=:updatedBy " +
                "where slug =:slug";

        Query query = entityManager.createQuery(strQuery);
        query.setParameter("username", userDto.getUsername());
        query.setParameter("email", userDto.getEmail());
        query.setParameter("password", userDto.getPassword());
        query.setParameter("contact", userDto.getContact());
        query.setParameter("userType", userDto.getUserType());
        query.setParameter("createAt", Utils.getCurrentMillis());
        query.setParameter("updatedAt", userDto.getUsername());
        query.setParameter("updatedBy", userDto.getUsername());
        query.setParameter("createdBy", userDto.getUsername());
        query.setParameter("slug", userDto.getUsername());
        query.executeUpdate();
        return  null;
    }
}
