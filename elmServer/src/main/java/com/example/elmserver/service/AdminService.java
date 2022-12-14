package com.example.elmserver.service;

import com.example.elmserver.dao.AdminDao;
import com.example.elmserver.entities.Admin;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class AdminService extends AbstractTypedService<Admin, Integer> {
    // @Resource
    AdminDao miDao;
    AdminService(AdminDao dao) {
        this.dataContext = dao;
        this.miDao = dao;
    }
    public List<Admin> queryAll() {

        List<Admin> lst = null;
        try {
            //
            lst = ((AdminDao) this.dataContext).findAll();
            log.info("find admin: " + lst.size());

        } catch (Exception e) {
            log.warn(e.toString());
            //throw new RuntimeException(e);
        }
        return lst;
    }
    public Page<Admin> queryMenusByPage(Pageable pageable) {
        return miDao.queryPage(pageable, null);
    }
}
