package com.example.elmserver.controllers.api.business;

import com.example.elmserver.controllers.AbstractTypedController;
import com.example.elmserver.entities.Business;
import com.example.elmserver.service.BusinessService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("business")
@Tag(name = "business控制器 V1")
public class BusinessController extends AbstractTypedController<Business, Integer> {
    BusinessController(BusinessService muSvc) {
        this.svcContext = muSvc;
    }
}
