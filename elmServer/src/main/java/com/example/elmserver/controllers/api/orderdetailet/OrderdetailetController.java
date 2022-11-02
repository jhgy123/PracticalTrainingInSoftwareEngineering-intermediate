package com.example.elmserver.controllers.api.orderdetailet;

import com.example.elmserver.controllers.AbstractTypedController;
import com.example.elmserver.entities.Business;
import com.example.elmserver.entities.Food;
import com.example.elmserver.entities.Orderdetailet;
import com.example.elmserver.entities.Orders;
import com.example.elmserver.service.OrderdetailetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("orderdetailet")
@Tag(name = "orderdetailet控制器 V1")
public class OrderdetailetController extends AbstractTypedController<Orderdetailet, Integer> {
    OrderdetailetService miService;
    OrderdetailetController(OrderdetailetService muSvc) {
        this.svcContext = muSvc;
        this.miService=muSvc;
    }

    @PostMapping("/findbyorders")
    @Operation(summary = "查询 根据订单编号查询订单详情")
    public List<Orderdetailet> findByOrders(@RequestBody Orders orders) {
        return miService.queryAllByOrders(orders);
    }

}
