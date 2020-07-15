package com.jackxue.pojo;

public class MyAddress {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "MyAddress{" +
                "address='" + address + '\'' +
                '}';
    }
}
