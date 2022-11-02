package com.example.elmserver.service;
import com.example.elmserver.dao.DeliveryaddressDao;
import com.example.elmserver.entities.Deliveryaddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeliveryaddressService extends AbstractTypedService<Deliveryaddress, Integer>{
    // @Resource
    DeliveryaddressDao miDao;

    DeliveryaddressService(DeliveryaddressDao dao) {
        this.dataContext = dao;
        this.miDao = dao;
    }
}
