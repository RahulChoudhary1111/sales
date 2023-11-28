package com.sales.admin.repositories;


import com.sales.dto.UserDto;
import com.sales.entities.User;
import com.sales.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Component
@Transactional
public class UserHbRepository {

    @Autowired
    EntityManager entityManager;

    public int updateUser(UserDto userDto,User loggedUser){
        String strQuery = "update User set " +
                "username=:username , " +
                "email=:email,"+
                "password=:password,"+
                "contact=:contact,"+
                "userType=:userType,"+
                "updatedAt=:updatedAt,"+
                "updatedBy=:updatedBy,"+
                "where slug =:slug";

        Query query = entityManager.createQuery(strQuery);
        query.setParameter("username", userDto.getUsername());
        query.setParameter("email", userDto.getEmail());
        query.setParameter("password", userDto.getPassword());
        query.setParameter("contact", userDto.getContact());
        query.setParameter("userType", userDto.getUserType());
        query.setParameter("updatedAt", Utils.getCurrentMillis());
        query.setParameter("updatedBy", loggedUser.getId());
        query.setParameter("slug", userDto.getSlug());
        return query.executeUpdate();
    }


    public int deleteUserBySlug(String slug){
        String hql = "Update User set isDeleted='Y' where slug=:slug";
        Query query = entityManager.createQuery(hql);
        query.setParameter("slug",slug);
        return query.executeUpdate();
    }
}
