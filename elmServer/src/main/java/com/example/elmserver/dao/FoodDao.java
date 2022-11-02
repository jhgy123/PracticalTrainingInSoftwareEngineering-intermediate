package com.example.elmserver.dao;
import com.example.elmserver.entities.Business;
import com.example.elmserver.entities.Food;
import java.util.List;

public interface FoodDao extends AbstractDao<Food, Integer>{
    /**
     * 根据商家查询菜品，主要是依据商家的ID进行查询
     * @param business 商家对象，主要是商家的id
     * @return List<Food> 菜品列表
     */
    List<Food> findAllByBusiness(Business business);
}
