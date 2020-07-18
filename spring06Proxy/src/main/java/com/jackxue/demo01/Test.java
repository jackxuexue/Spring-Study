package com.jackxue.demo01;

public class Test {
    public static void main(String[] args) {
        //房东类
        Host host = new Host();

        ProxyInnovationHandler pih = new ProxyInnovationHandler();
        pih.setTarget(host);

        //动态生成代理类
        Rent proxy = (Rent) pih.getProxy();
        proxy.RentHouse();
    }
}
