package com.example.elmserver.controllers.api.admin;


import com.example.elmserver.controllers.AbstractTypedController;
import com.example.elmserver.entities.Admin;
import com.example.elmserver.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("admin")
@Tag(name = "admin控制器 V1")
public class AdminController extends AbstractTypedController<Admin, Integer> {
    AdminController(AdminService muSvc) {
        this.svcContext = muSvc;
    }


    @Operation(summary = "登录接口")
    @GetMapping("/login")
    public Admin login() {
        Admin usr = null;
//          usr = usDao.findUserByUsernameAndPassword("admin", "{noop}332211Arc");
        return usr;
    }
}
