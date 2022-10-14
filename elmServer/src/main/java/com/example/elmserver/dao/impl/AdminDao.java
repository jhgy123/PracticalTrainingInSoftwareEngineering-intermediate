package com.example.elmserver.dao.impl;

import com.example.elmserver.utils.DBHelper;
import com.example.elmserver.dao.IAdminDao;
import com.example.elmserver.entities.Admin;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminDao implements IAdminDao {

    @Resource
    DBHelper dbhelper;


    @Override
    public boolean save(Admin s) throws SQLException {
        var sql="insert into Admin_Inf(name,password) "+
        "values('"+s.getName()+"','"+s.getPassword()+"')";
        var resultnum=dbhelper.executeUpdate(sql);
        return resultnum>0;
    }

    @Override
    public boolean update(Admin s) throws SQLException {
        var sql="update Admin_Inf set name='"+s.getName()+"',password='"+s.getPassword()+"' " +
                "where id="+s.getId();
        var resultnum=dbhelper.executeUpdate(sql);
        return resultnum>0;
    }

    @Override
    public boolean delete(int i) throws SQLException {
        var sql="delete from Admin_Inf where id="+i;
        var resultnum=dbhelper.executeUpdate(sql);
        return resultnum>0;
    }

    @Override
    public Admin getOne(int i) throws SQLException {
        var sql="select * from Admin_Inf where id=" + i ;
        var resultset=dbhelper.executeQuery(sql);
        if(resultset.next()) {
            var admin = Admin.builder()
                    .id(resultset.getInt("id"))
                    .password(resultset.getString("password"))
                    .name(resultset.getString("name"))
                    .build();
//            System.out.println(admin);
            return admin;
        }
        else{
            return null;
        }

    }

    @Override
    public List<Admin> getAll() throws SQLException {
        List<Admin>  result= new ArrayList<Admin>();
        var sql="select * from Admin_Inf";
        var resultset=dbhelper.executeQuery(sql);
        while(resultset.next()){
            var admin = Admin.builder()
                    .id(resultset.getInt("id"))
                    .password(resultset.getString("password"))
                    .name(resultset.getString("name"))
                    .build();
//            System.out.println(admin);
            result.add(admin);
        }
        return result;
    }
}
