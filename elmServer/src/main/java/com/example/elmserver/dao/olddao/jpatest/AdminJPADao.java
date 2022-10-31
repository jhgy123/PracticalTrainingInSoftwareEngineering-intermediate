package com.example.elmserver.dao.olddao.jpatest;

import com.example.elmserver.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdminJPADao extends JpaRepository<Admin,Integer> , JpaSpecificationExecutor<Admin> {

    Admin findAdminByIdAndPassword(Integer id,String password);//jpa的方法

//    default Page<Admin> queryByPage(Pageable pageable, @Nullable Specification<Admin> spec){
//        return this.findAll(spec,pageable)
//    }


}
