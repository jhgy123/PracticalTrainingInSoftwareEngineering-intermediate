package com.example.elmserver.controllers.api.cart;

import com.example.elmserver.controllers.AbstractTypedController;
import com.example.elmserver.entities.Cart;
import com.example.elmserver.service.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("cart")
@Tag(name = "cart控制器 V1")
public class CartController extends AbstractTypedController<Cart, Integer> {
    CartController(CartService muSvc) {
        this.svcContext = muSvc;
    }
}
