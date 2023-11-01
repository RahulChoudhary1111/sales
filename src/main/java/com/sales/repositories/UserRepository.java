package com.sales.repositories;


import com.sales.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {

    @Query(value = "from User where email=:email and password=:password")
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);


    User findUserBySlug(String slug);





}
