package com.example.elmserver.service;

import com.example.elmserver.dao.FoodDao;
import com.example.elmserver.entities.Food;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FoodService extends AbstractTypedService<Food, Integer>{
    // @Resource
    FoodDao miDao;

    FoodService(FoodDao dao) {
        this.dataContext = dao;
        this.miDao = dao;
    }
}
