package com.jackxue.diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AnnotationLog {
    @Before("execution(* com.jackxue.services.UserServicesImpl.*(..))")
    public void before(){
        System.out.println("方法执行前================");
    }
    @After("execution(* com.jackxue.services.UserServicesImpl.*(..))")
    public void after(){
        System.out.println("方法执行后================");
    }
    @AfterReturning("execution(* com.jackxue.services.UserServicesImpl.*(..))")
    public void afterreturn(){
        System.out.println("方法返回后================");
    }
    @Around("execution(* com.jackxue.services.UserServicesImpl.*(..))")
    public void aroud(ProceedingJoinPoint jb) throws Throwable {
        System.out.println("环绕前===================");

        Object proceed = jb.proceed();

        System.out.println("环绕后===================");
    }
}
