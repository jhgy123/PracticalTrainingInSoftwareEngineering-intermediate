package com.example.elmserver.dao;

import com.example.elmserver.entities.Business;
import com.example.elmserver.entities.Deliveryaddress;
import com.example.elmserver.entities.Food;
import com.example.elmserver.entities.User;
import java.util.List;

public interface DeliveryaddressDao extends AbstractDao<Deliveryaddress, Integer>{
    /**
     * 根据用户查询出所有的收货地址
     * @param user 用户对象
     * @return List<Deliveryaddress> 收货地址list
     */
    List<Deliveryaddress> findAllByUser(User user);
}
