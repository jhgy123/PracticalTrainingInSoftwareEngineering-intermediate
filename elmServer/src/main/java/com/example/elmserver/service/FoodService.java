package com.example.elmserver.service;

import com.example.elmserver.dao.FoodDao;
import com.example.elmserver.entities.Business;
import com.example.elmserver.entities.Deliveryaddress;
import com.example.elmserver.entities.Food;
import com.example.elmserver.entities.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class FoodService extends AbstractTypedService<Food, Integer>{
    // @Resource
    FoodDao miDao;
    FoodService(FoodDao dao) {
        this.dataContext = dao;
        this.miDao = dao;
    }
    @Operation(summary = "根据商家查询菜品列表")
    public List<Food> queryAllByBusiness(Business business){
        if(business==null){
            return new LinkedList<>();
        }
        return miDao.findAllByBusiness(business);
    }
}
