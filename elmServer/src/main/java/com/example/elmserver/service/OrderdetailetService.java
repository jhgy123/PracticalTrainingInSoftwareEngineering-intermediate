package com.example.elmserver.service;

import com.example.elmserver.dao.OrderdetailetDao;
import com.example.elmserver.entities.Business;
import com.example.elmserver.entities.Food;
import com.example.elmserver.entities.Orderdetailet;
import com.example.elmserver.entities.Orders;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class OrderdetailetService extends AbstractTypedService<Orderdetailet, Integer>{
    // @Resource
    OrderdetailetDao miDao;
    OrderdetailetService(OrderdetailetDao dao) {
        this.dataContext = dao;
        this.miDao = dao;
    }
    @Operation(summary = "根据订单号查询订单详情")
    public List<Orderdetailet> queryAllByOrders(Orders orders){
        if(orders==null){
            return new LinkedList<>();
        }
        return miDao.findAllByOrders(orders);
    }
}
