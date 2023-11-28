package com.sales.admin.repositories;


import com.sales.dto.AddressDto;
import com.sales.entities.User;
import com.sales.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Component
@Transactional
public class AddressHbRepository {

    @Autowired
    EntityManager entityManager;

    public int updateAddress(AddressDto addressDto, User loggedUser){
        String hqQuery ="update Address set " +
                "city =:city,"+
                "state =:state," +
                "latitude =:latitude," +
                "altitude =:altitude, " +
                "updatedAt =:updatedAt " +
                "updatedBy =:updatedBy " +
                "where slug =:slug ";
        Query query = entityManager.createQuery(hqQuery);
        query.setParameter("city",addressDto.getCity());
        query.setParameter("state",addressDto.getState());
        query.setParameter("latitude",addressDto.getLatitude());
        query.setParameter("altitude",addressDto.getAltitude());
        query.setParameter("updatedBy", loggedUser.getId());
        query.setParameter("updatedAt", Utils.getCurrentMillis());
        query.setParameter("slug",addressDto.getSlug());
        return  query.executeUpdate();

    }

}
