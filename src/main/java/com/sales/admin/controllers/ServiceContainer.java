package com.sales.admin.controllers;

import com.sales.admin.services.ItemService;
import com.sales.admin.services.StoreService;
import com.sales.admin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceContainer {
    @Autowired
    protected StoreService storeService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected ItemService itemService;
}
