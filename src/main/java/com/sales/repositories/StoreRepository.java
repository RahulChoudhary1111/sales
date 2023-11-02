package com.sales.repositories;

import com.sales.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
    @Query(value = "from Store where email=:email and name=:storeName")
    Store findByStoreNameAndEmail(@Param("email") String email, @Param("name") String storeName);

}
