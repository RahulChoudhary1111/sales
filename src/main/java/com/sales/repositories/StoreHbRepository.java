package com.sales.repositories;

import com.sales.dto.StoreDto;
import com.sales.entities.User;
import com.sales.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Component
@Transactional
public class StoreHbRepository {

    @Autowired
    EntityManager entityManager;

    public int deleteStore(String slug){
        String hql = "Update Store set isDeleted='Y' where slug=:slug";
        Query query = entityManager.createQuery(hql);
        query.setParameter("slug",slug);
        return query.executeUpdate();
    }

    public int updateStore(StoreDto storeDto, User loggedUser){
        String strQuery = "update Store set " +
                "storeName=:name , " +
                "email=:email, "+
                "address=:address, "+
                "phone=:phone, "+
                "rating=:rating, "+
                "longitude=:longitude, "+
                "latitude=:latitude, "+
                "description=:description, "+
                "updatedAt=:updatedAt, "+
                "updatedBy=:updatedBy "+
                "where slug =:slug";

        Query query = entityManager.createQuery(strQuery);
        query.setParameter("name", storeDto.getStoreName());
        query.setParameter("email", storeDto.getEmail());
        query.setParameter("address", storeDto.getAddress());
        query.setParameter("phone", storeDto.getPhone());
        query.setParameter("rating", storeDto.getRating());
        query.setParameter("longitude", storeDto.getLongitude());
        query.setParameter("latitude", storeDto.getLatitude());
        query.setParameter("description", storeDto.getDescription());
        query.setParameter("updatedAt", Utils.getCurrentMillis());
        query.setParameter("updatedBy", loggedUser.getId());
        query.setParameter("slug", storeDto.getSlug());
        return query.executeUpdate();
    }


}
