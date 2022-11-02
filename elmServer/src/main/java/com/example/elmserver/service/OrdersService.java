package com.example.elmserver.service;
import com.example.elmserver.dao.OrdersDao;
import com.example.elmserver.entities.Orders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrdersService extends AbstractTypedService<Orders, Integer>{
    // @Resource
    OrdersDao miDao;

    OrdersService(OrdersDao dao) {
        this.dataContext = dao;
        this.miDao = dao;
    }
}
