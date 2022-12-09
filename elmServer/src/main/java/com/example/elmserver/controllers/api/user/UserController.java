package com.example.elmserver.controllers.api.user;

import com.example.elmserver.controllers.AbstractTypedController;
import com.example.elmserver.entities.User;
import com.example.elmserver.service.UserService;
import com.example.elmserver.utils.SessionUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("user")
@Tag(name = "user控制器 V1")
public class UserController extends AbstractTypedController<User, String> {
    UserService miService;
    UserController(UserService muSvc) {
        this.svcContext = muSvc;
        this.miService=muSvc;
    }
    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public User loginf(@RequestParam String id, @RequestParam String password) {
        return miService.queryUserByIdAndPassword(id,password);
    }

}
