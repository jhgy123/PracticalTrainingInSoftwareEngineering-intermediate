package com.example.elmserver.service;

import com.example.elmserver.dao.BusinessDao;
import com.example.elmserver.entities.Business;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
