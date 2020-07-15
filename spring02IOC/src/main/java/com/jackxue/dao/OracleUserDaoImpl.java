package com.jackxue.dao;

public class OracleUserDaoImpl  implements UserDao{

    private String str;
    public OracleUserDaoImpl(String str){
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void getUser() {
        System.out.println(this.str);
    }

    @Override
    public String toString() {
        return "OracleUserDaoImpl{" +
                "str='" + str + '\'' +
                '}';
    }


}
