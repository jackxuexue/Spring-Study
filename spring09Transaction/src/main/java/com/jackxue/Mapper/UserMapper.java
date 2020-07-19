package com.jackxue.Mapper;

import com.jackxue.pojo.User;

import java.util.List;

public interface UserMapper {
    public abstract List<User> getUsers();
    public abstract int updateUser(User user);
    public abstract int insertUser(User user);
    public abstract int deleteUser(int id);
}
