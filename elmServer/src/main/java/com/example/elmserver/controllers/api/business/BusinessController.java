package com.example.elmserver.controllers.api.business;

import com.example.elmserver.controllers.AbstractTypedController;
import com.example.elmserver.entities.Business;
import com.example.elmserver.service.BusinessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("business")
@Tag(name = "business控制器 V1")
public class BusinessController extends AbstractTypedController<Business, Integer> {
    BusinessService miService;
    BusinessController(BusinessService muSvc) {
        this.svcContext = muSvc;
        this.miService=muSvc;
    }

    @GetMapping("/findbyordertypeid/{ordertypeid}")
    @Operation(summary = "查询 根据分类编号查询数据实体列表")
    public List<Business> findByOrderTypeId(@PathVariable int ordertypeid) {
        return miService.queryAllByOrderTypeId(ordertypeid);
    }
}
