package com.sales.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
public class StoreHbRepository {

    @Autowired
    EntityManager entityManager;

//    public int update

}
