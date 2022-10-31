package com.example.elmserver.controllers.oldcontrollers;

import com.example.elmserver.configuration.properies.MySecurityProperites;
import com.example.elmserver.dao.olddao.impl.OldAdminDao;
import com.example.elmserver.dao.olddao.jpatest.AdminJPADao;
import com.example.elmserver.entities.Admin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLException;

@Slf4j
@Tag(name="管理员控制器 old")
@RestController
@RequestMapping(value = "oldadmin")
public class OldAdminController {

    @Resource
    OldAdminDao admindao;

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
    @Operation(summary = "登录")
    @GetMapping("/login")
    public Admin login() {
        Admin admin=null;
        admin= usDao.findAdminByIdAndPassword(1,"0000");
        log.info("-------------------test-------------");
        return admin;
    }

//    @Operation(summary = "查询 分页")
//    @RequestMapping(value = "/Pgae",method = RequestMethod.GET)
//    public Page<MenuItem> queryMenusByPage(Pageable pageable){
//        return null;
//    }
}
