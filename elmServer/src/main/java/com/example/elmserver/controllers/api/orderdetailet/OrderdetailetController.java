package com.example.elmserver.controllers.api.orderdetailet;

import com.example.elmserver.controllers.AbstractTypedController;
import com.example.elmserver.entities.Orderdetailet;
import com.example.elmserver.service.OrderdetailetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("orderdetailet")
@Tag(name = "orderdetailet控制器 V1")
public class OrderdetailetController extends AbstractTypedController<Orderdetailet, Integer> {
    OrderdetailetController(OrderdetailetService muSvc) {
        this.svcContext = muSvc;
    }
}
