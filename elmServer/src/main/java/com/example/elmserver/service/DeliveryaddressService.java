package com.example.elmserver.service;
import com.example.elmserver.dao.DeliveryaddressDao;
import com.example.elmserver.entities.Business;
import com.example.elmserver.entities.Deliveryaddress;
import com.example.elmserver.entities.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class DeliveryaddressService extends AbstractTypedService<Deliveryaddress, Integer>{
    // @Resource
    DeliveryaddressDao miDao;

    DeliveryaddressService(DeliveryaddressDao dao) {
        this.dataContext = dao;
        this.miDao = dao;
    }
    @Operation(summary = "根据用户查询收货地址列表")
    public List<Deliveryaddress> queryAllByUser(User user){
        if(user==null){
            return new LinkedList<>();
        }
        return miDao.findAllByUser(user);
    }
}
