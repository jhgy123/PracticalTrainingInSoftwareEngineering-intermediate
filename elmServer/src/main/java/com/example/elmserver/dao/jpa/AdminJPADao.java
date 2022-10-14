package com.example.elmserver.dao.jpa;

import com.example.elmserver.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminJPADao extends JpaRepository<Admin,Integer> {

    Admin findAdminByIdAndPassword(Integer id,String password);//jpa的方法
}
