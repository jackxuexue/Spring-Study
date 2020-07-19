package com.jackxue.Mapper;

import com.jackxue.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {
    public List<User> getUsers() {
        insertUser(new User(0, "张三丰",02));
        int a = 10/0;
        UserMapper mapper = getSqlSession().getMapper(UserMapper.class);
        return mapper.getUsers();
    }

    public int updateUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).updateUser(user);
    }

    public int insertUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).insertUser(user);
    }

    public int deleteUser(int id) {
        return getSqlSession().getMapper(UserMapper.class).deleteUser(id);
    }
}
