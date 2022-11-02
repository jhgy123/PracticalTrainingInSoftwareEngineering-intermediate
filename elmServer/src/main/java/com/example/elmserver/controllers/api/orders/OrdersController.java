package com.example.elmserver.controllers.api.orders;

import com.example.elmserver.controllers.AbstractTypedController;
import com.example.elmserver.entities.Orders;
import com.example.elmserver.service.OrdersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("orders")
@Tag(name = "orders控制器 V1")
public class OrdersController extends AbstractTypedController<Orders, Integer> {
    OrdersController(OrdersService muSvc) {
        this.svcContext = muSvc;
    }
}
