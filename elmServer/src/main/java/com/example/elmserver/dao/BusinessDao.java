package com.example.elmserver.dao;


import com.example.elmserver.entities.Business;

import java.util.Collection;
import java.util.List;

public interface BusinessDao extends AbstractDao<Business, Integer>{
    /**
     * 根据点餐分类进行查询
     * @param orderTypeId 点餐分类编号
     * @return List<Business> 返回商家list
     */
    List<Business> findAllByOrderTypeId(int orderTypeId);
}
