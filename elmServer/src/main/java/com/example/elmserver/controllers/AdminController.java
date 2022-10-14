package com.example.elmserver.controllers;

import com.example.elmserver.configuration.properies.MySecurityProperites;
import com.example.elmserver.dao.jpa.AdminJPADao;
import com.example.elmserver.entities.Admin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.SQLException;


@RestController
@RequestMapping(value = "admin")
public class AdminController {

    @Resource
    com.example.elmserver.dao.impl.AdminDao admindao;

    @GetMapping("/getone")
    //接收get请求中的id参数值
    public String getOneAdminById(@RequestParam int id) throws SQLException {

//        System.out.println(admindao.getOne(1));

        return admindao.getOne(id).toString();
    }

    @GetMapping("/getall")
    public String getAllAdminById() throws SQLException {

//        System.out.println(admindao.getAll());

        return admindao.getAll().toString();
    }

    @Resource
    MySecurityProperites secPps;
    @GetMapping("/test")
    public String getProps(){
        var userName=secPps.getDburl();
        System.out.println(userName);
        return userName;
    }

    @Resource
    AdminJPADao usDao;//jpa的dao
    @GetMapping("/login")
    public Admin login() {
        Admin admin=null;
        admin= usDao.findAdminByIdAndPassword(1,"0000");
        return admin;
    }



}
