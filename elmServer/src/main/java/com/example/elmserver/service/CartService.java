package com.example.elmserver.service;

import com.example.elmserver.dao.CartDao;
import com.example.elmserver.entities.Cart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CartService extends AbstractTypedService<Cart, Integer>{
    // @Resource
    CartDao miDao;

    CartService(CartDao dao) {
        this.dataContext = dao;
        this.miDao = dao;
    }
}
