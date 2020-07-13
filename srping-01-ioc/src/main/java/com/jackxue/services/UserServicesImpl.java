package com.jackxue.services;

import com.jackxue.dao.MysqlUserDaoImpl;
import com.jackxue.dao.UserDao;
import com.jackxue.dao.UserDaoImpl;

public class UserServicesImpl implements UserServices {
    // 控制权在程序猿上，切换实现类时需要修改源代码，非常麻烦
//    UserDao dao = new MysqlUserDaoImpl();
    // 通过set方法来，让用户自定义设置实现类，这个就是IOC的原理
    UserDao dao ;

    public UserDao getDao() {
        return dao;
    }

    public void setDao(UserDao dao) {
        this.dao = dao;
    }

    public void getUser() {
        dao.getUser();
    }
}
