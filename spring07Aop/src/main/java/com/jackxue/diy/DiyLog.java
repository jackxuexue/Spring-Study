package com.jackxue.diy;

public class DiyLog {
    public void before(){
        System.out.println("方法执行前==============");
    }

    public void after(){
        System.out.println("方法执行后==============");
    }

    public void afterreturn(){
        System.out.println("方法执行后的返回==============");
    }
}
