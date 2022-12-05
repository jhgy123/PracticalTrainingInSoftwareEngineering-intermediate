package com.example.elmserver.service;

import com.example.elmserver.dao.UserDao;
import com.example.elmserver.entities.Orders;
import com.example.elmserver.entities.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class UserService extends AbstractTypedService<User, String>{
    // @Resource
    UserDao miDao;

    UserService(UserDao dao) {
        this.dataContext = dao;
        this.miDao = dao;
    }

    public User getUserById(String id) {
        if (id == null || id == "") {
            return null;
        }
        return miDao.findById(id).orElse(null);
    }

    /**
     * @return User
     */
    public User getUserByUserName(String userName) {

        var us = this.miDao.findUserByUsername(userName);

        return us;
    }

    @Operation(summary = "根据用户id和密码查询用户")
    public User queryUserByIdAndPassword(String id,String password){
        if(id==""||password==""||id==null||password==null){
            return null;
        }
        return miDao.findUserByIdAndPassword(id,password);
    }
}
