package com.example.elmserver.controllers;

import com.example.elmserver.dao.impl.BusinessDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.SQLException;

@RestController
@RequestMapping(value = "business")
public class BusinessController {

    @Resource
    BusinessDao businessdao;

    @GetMapping("/getone")
    //接收get请求中的id参数值
    public String getOneAdminById(@RequestParam int id) throws SQLException {

        System.out.println(businessdao.getOne(id));

        return businessdao.getOne(id).toString();
    }

    @GetMapping("/getall")
    public String getAllAdminById() throws SQLException {

        System.out.println(businessdao.getAll());

        return businessdao.getAll().toString();
    }
}
