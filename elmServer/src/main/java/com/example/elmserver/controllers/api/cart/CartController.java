package com.example.elmserver.controllers.api.cart;

import com.example.elmserver.controllers.AbstractTypedController;
import com.example.elmserver.entities.*;
import com.example.elmserver.service.BusinessService;
import com.example.elmserver.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("cart")
@Tag(name = "cart控制器 V1")
public class CartController extends AbstractTypedController<Cart, Integer> {
    CartService miService;
    CartController(CartService muSvc) {
        this.svcContext = muSvc;
        this.miService=muSvc;
    }
    @GetMapping("/findcartsbybusinessanduser")
    @Operation(summary = "查询 根据商家和用户查询购物车列表")
    public List<Cart> findCartsByBusinessAndUser(@RequestParam Integer businessid,@RequestParam String userid) {
        return miService.queryCartsByBusinessAndUser(businessid,userid);
    }

    @PostMapping("/batchdeletebycarts")
    @Operation(summary = "根据对象列表批量删除")
    public HttpStatus batchDeleteByCarts(@RequestBody List<Cart> carts) {
        miService.deleteAllByCarts(carts);
        return HttpStatus.OK;
    }


}
