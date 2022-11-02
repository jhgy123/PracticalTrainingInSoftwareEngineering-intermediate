package com.example.elmserver.controllers.api.deliveryaddress;

import com.example.elmserver.controllers.AbstractTypedController;
import com.example.elmserver.entities.Business;
import com.example.elmserver.entities.Deliveryaddress;
import com.example.elmserver.entities.User;
import com.example.elmserver.service.DeliveryaddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("deliveryaddress")
@Tag(name = "deliveryaddress控制器 V1")
public class DeliveryaddressController extends AbstractTypedController<Deliveryaddress, Integer> {
    DeliveryaddressService miService;
    DeliveryaddressController(DeliveryaddressService muSvc) {
        this.svcContext = muSvc;
        this.miService=muSvc;
    }

    @PostMapping("/findbyuser")
    @Operation(summary = "查询 根据用户查询地址列表")
    public List<Deliveryaddress> findByUser(@RequestBody User user) {
        return miService.queryAllByUser(user);
    }
}
