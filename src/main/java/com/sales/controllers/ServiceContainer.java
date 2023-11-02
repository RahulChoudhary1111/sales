package com.sales.controllers;

import com.sales.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceContainer {

    @Autowired
    protected UserService userService;
}
