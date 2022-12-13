package com.example.elmserver.controllers.api.orders;

import com.example.elmserver.controllers.AbstractTypedController;
import com.example.elmserver.entities.Orderdetailet;
import com.example.elmserver.entities.Orders;
import com.example.elmserver.entities.User;
import com.example.elmserver.service.OrdersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("orders")
@Tag(name = "orders控制器 V1")
public class OrdersController extends AbstractTypedController<Orders, Integer> {
    OrdersService miService;
    OrdersController(OrdersService muSvc) {
        this.svcContext = muSvc;
        this.miService=muSvc;
    }

    @PostMapping("/historyorders")
    @Operation(summary = "查询 历史订单")
    public List<Orders> findHistoryOrders(@RequestBody User user,@RequestParam Boolean state) {
        return miService.queryAllByUserAndOrderState(user,state);
    }

    @PutMapping("/pay")
    @Operation(summary = "修改 支付订单")
    public Orders update(@RequestBody Orders item) {
        return miService.payorder(item);
    }
}
