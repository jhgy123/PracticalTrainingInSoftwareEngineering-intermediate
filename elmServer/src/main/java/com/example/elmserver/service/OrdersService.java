package com.example.elmserver.service;
import com.example.elmserver.dao.OrdersDao;
import com.example.elmserver.entities.Orderdetailet;
import com.example.elmserver.entities.Orders;
import com.example.elmserver.entities.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class OrdersService extends AbstractTypedService<Orders, Integer>{
    // @Resource
    OrdersDao miDao;

    OrdersService(OrdersDao dao) {
        this.dataContext = dao;
        this.miDao = dao;
    }
    @Operation(summary = "根据用户和订单状态查询订单")
    public List<Orders> queryAllByUserAndOrderState(User user, Boolean state){
        if(user==null||state==null){
            return new LinkedList<>();
        }
        return miDao.findAllByUserAndOrderState(user,state);
    }
}
