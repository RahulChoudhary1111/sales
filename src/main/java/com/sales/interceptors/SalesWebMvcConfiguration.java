package com.sales.interceptors;

import com.sales.jwtUtils.JwtToken;
import com.sales.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class SalesWebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    JwtToken jwtToken;

    @Autowired
    UserRepository userRepository;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SalesInterceptor(jwtToken,userRepository))
                .excludePathPatterns("/login");
        super.addInterceptors(registry);
    }

}
