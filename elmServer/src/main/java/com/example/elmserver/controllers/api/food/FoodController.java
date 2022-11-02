package com.example.elmserver.controllers.api.food;

import com.example.elmserver.controllers.AbstractTypedController;
import com.example.elmserver.entities.Business;
import com.example.elmserver.entities.Deliveryaddress;
import com.example.elmserver.entities.Food;
import com.example.elmserver.entities.User;
import com.example.elmserver.service.FoodService;
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
@RequestMapping("food")
@Tag(name = "food控制器 V1")
public class FoodController extends AbstractTypedController<Food, Integer> {
    FoodService miService;
    FoodController(FoodService muSvc) {
        this.svcContext = muSvc;
        this.miService=muSvc;
    }

    @PostMapping("/findbybusiness")
    @Operation(summary = "查询 根据商家查询菜品列表")
    public List<Food> findByBusiness(@RequestBody Business business) {
        return miService.queryAllByBusiness(business);
    }
}
