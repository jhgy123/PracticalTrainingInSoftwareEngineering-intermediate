package com.example.elmserver.controllers.api.user;

import com.example.elmserver.controllers.AbstractTypedController;
import com.example.elmserver.entities.User;
import com.example.elmserver.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("user")
@Tag(name = "user控制器 V1")
public class UserController extends AbstractTypedController<User, String> {
    UserController(UserService muSvc) {
        this.svcContext = muSvc;
    }
}
