package com.sales.admin.repositories;


import com.sales.dto.ItemDto;
import com.sales.entities.User;
import com.sales.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Component
@Transactional
public class ItemHbRepository{

    @Autowired
    EntityManager entityManager;
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "price")
    float price;
    @Column(name = "discount")
    float discount;
    @Column(name = "description")
    String description;

    public int updateItems(ItemDto itemDto, User loggedUser){
        String hqQuery = "update Item set " +
                "name =:name," +
                "description =:description," +
                "price =:price," +
                "discount =:discount," +
                "updatedAt =:updatedAt," +
                "updatedBy =:updatedBy " +
                "where slug =:slug ";
        Query query = entityManager.createQuery(hqQuery);
        query.setParameter("name" , itemDto.getName());
        query.setParameter("description" , itemDto.getDescription());
        query.setParameter("price" , itemDto.getPrice());
        query.setParameter("discount" , itemDto.getDiscount());
        query.setParameter("updatedAt" , Utils.getCurrentMillis());
        query.setParameter("updatedBy" , loggedUser.getId());
        query.setParameter("slug",itemDto.getSlug());
        return  query.executeUpdate();
    }


    public int deleteItem(String slug){
        String hqlString = "update Item set isDeleted='Y' where slug=:slug";
        Query query = entityManager.createQuery(hqlString);
        query.setParameter("slug",slug);
        return query.executeUpdate();
    }

    public int updateStock(String stock , String slug){
        String hqlString = "update Item set inStock=:stock where slug=:slug";
        Query query = entityManager.createQuery(hqlString);
        query.setParameter("stock",stock);
        query.setParameter("slug",slug);
        return query.executeUpdate();
    }


}
