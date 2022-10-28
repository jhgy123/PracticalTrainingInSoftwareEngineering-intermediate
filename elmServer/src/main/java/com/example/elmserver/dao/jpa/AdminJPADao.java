package com.example.elmserver.dao.jpa;

import com.example.elmserver.entities.Admin;
import com.github.pagehelper.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;

import java.awt.print.Pageable;

public interface AdminJPADao extends JpaRepository<Admin,Integer> , JpaSpecificationExecutor<Admin> {

    Admin findAdminByIdAndPassword(Integer id,String password);//jpa的方法

//    default Page<Admin> queryByPage(Pageable pageable, @Nullable Specification<Admin> spec){
//        return this.findAll(spec,pageable)
//    }


}
