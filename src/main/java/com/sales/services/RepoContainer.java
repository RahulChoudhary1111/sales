package com.sales.services;


import com.sales.repositories.UserHbRepository;
import com.sales.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepoContainer {


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserHbRepository userHbRepository;

}
