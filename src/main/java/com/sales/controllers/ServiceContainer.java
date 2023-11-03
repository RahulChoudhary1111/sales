package com.sales.controllers;
import com.sales.services.StoreService;
import com.sales.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sales.services.ItemService;
        ;
@Component
public class ServiceContainer {
    @Autowired
    protected StoreService storeService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected ItemService itemService;
}
