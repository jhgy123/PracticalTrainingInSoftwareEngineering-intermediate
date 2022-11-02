package com.example.elmserver.service;

import com.example.elmserver.dao.OrderdetailetDao;
import com.example.elmserver.entities.Orderdetailet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderdetailetService extends AbstractTypedService<Orderdetailet, Integer>{
    // @Resource
    OrderdetailetDao miDao;

    OrderdetailetService(OrderdetailetDao dao) {
        this.dataContext = dao;
        this.miDao = dao;
    }
}
