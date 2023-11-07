package com.sales.services;


import com.sales.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepoContainer {

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    StoreHbRepository storeHbRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserHbRepository userHbRepository;

    @Autowired
    ItemRepository itemRepository;
}
