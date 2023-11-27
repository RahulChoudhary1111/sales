package com.sales.interceptors;

import com.sales.admin.repositories.UserRepository;
import com.sales.jwtUtils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;


@Configuration
@EnableWebMvc
public class StoreWebMvcConfigure implements WebMvcConfigurer {

    @Autowired
    JwtToken jwtToken;

    @Autowired
    UserRepository userRepository;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String arr[] = {
                "/login",
                "/v3/api-docs",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/webjars/**",

        };
        registry.addInterceptor(new SalesInterceptor(jwtToken,userRepository))
                .excludePathPatterns(Arrays.asList(arr));
    }



}
