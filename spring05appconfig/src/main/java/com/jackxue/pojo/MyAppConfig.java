package com.jackxue.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//这个也会被Spring容器托管，注册到容器中，因为他也是一个@Component
//@Configuration 代表了这是一个配置类，就和我们的之前的beans.xml，所以xml中的扫描包之类的他都可以加注解
@Configuration
@ComponentScan("com.jackxue.pojo")  //扫描包之类的他都可以加注解
public class MyAppConfig {
    //配置为bean，就相当于之前我们在xml中配置的<bean>标签
    @Bean
    //getUser方法名就像我们之前的<bean id="getUser">
    public User getUser(){
        return new User();
    }
}
