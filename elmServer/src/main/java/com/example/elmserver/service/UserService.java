package com.example.elmserver.service;

import com.example.elmserver.dao.UserDao;
import com.example.elmserver.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService extends AbstractTypedService<User, String>{
    // @Resource
    UserDao miDao;

    UserService(UserDao dao) {
        this.dataContext = dao;
        this.miDao = dao;
    }
}
