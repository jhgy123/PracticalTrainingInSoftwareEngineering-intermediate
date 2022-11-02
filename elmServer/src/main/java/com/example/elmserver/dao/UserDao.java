package com.example.elmserver.dao;

import com.example.elmserver.entities.User;

public interface UserDao extends AbstractDao<User, String>{
    /**
     * 根据用户id和密码查询指定的用户
     * @param id 用户id(主键）
     * @param password 用户密码
     * @return User 用户对象/。/
     */
    User findUserByIdAndPassword(String id,String password);
}
