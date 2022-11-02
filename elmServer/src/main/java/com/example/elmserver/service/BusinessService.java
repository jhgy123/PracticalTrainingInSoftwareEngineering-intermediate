package com.example.elmserver.service;

import com.example.elmserver.dao.BusinessDao;
import com.example.elmserver.entities.Business;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BusinessService extends AbstractTypedService<Business, Integer>{
    // @Resource
    BusinessDao miDao;

    //其他DAO
    // @Resource
    // UserDao udDao;

    BusinessService(BusinessDao dao) {
        this.dataContext = dao;
        this.miDao = dao;
    }

    @Operation(summary = "根据点餐分类编号获取商家对象列表")
    public List<Business> queryAllByOrderTypeId(int orderTypeId){
        return miDao.findAllByOrderTypeId(orderTypeId);
    }

}
