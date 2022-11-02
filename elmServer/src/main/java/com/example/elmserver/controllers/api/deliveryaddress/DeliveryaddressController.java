package com.example.elmserver.controllers.api.deliveryaddress;

import com.example.elmserver.controllers.AbstractTypedController;
import com.example.elmserver.entities.Deliveryaddress;
import com.example.elmserver.service.DeliveryaddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("deliveryaddress")
@Tag(name = "deliveryaddress控制器 V1")
public class DeliveryaddressController extends AbstractTypedController<Deliveryaddress, Integer> {
    DeliveryaddressController(DeliveryaddressService muSvc) {
        this.svcContext = muSvc;
    }
}
