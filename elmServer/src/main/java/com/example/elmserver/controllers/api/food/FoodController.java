package com.example.elmserver.controllers.api.food;

import com.example.elmserver.controllers.AbstractTypedController;
import com.example.elmserver.entities.Food;
import com.example.elmserver.service.FoodService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("food")
@Tag(name = "food控制器 V1")
public class FoodController extends AbstractTypedController<Food, Integer> {
    FoodController(FoodService muSvc) {
        this.svcContext = muSvc;
    }
}
