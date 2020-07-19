package com.jackxue.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.jackxue.pojo")  //扫描包之类的他都可以加注解
public class MyAppConfig2 {
    @Bean
    public User user(){
        return new User();
    }
}
