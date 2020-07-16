package com.jackxue.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //配置类
public class MyAppConfig {
    @Bean  //配置为bean
    public User getUser(){
        return new User();
    }
}
