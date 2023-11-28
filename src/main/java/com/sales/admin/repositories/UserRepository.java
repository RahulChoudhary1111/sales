package com.sales.admin.repositories;


import com.sales.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer> , JpaSpecificationExecutor {

    @Query(value = "from User where email=:email and password=:password and userType='S' ")
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);


    User findUserBySlug(String slug);


}
