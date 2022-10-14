package com.example.elmserver.dao.impl;

import com.example.elmserver.utils.DBHelper;
import com.example.elmserver.dao.IBusinessDao;
import com.example.elmserver.entities.Business;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BusinessDao implements IBusinessDao {

    @Resource
    DBHelper dbhelper;

    @Override
    public boolean save(Business s) throws SQLException {
        var sql="insert into Business_Inf(name,password,address,explain,starPrice,deliveryPrice) "+
                "values('"+s.getName()+"','"+s.getPassword()+"','"+s.getAddress()+"','"+s.getExplain()
                +"',"+s.getStarPrice()+","+s.getDeliveryPrice()+")";
        var resultnum=dbhelper.executeUpdate(sql);
        return resultnum>0;
    }

    @Override
    public boolean update(Business s) throws SQLException {
        var sql="update Business_Inf set name='"+s.getName()+
                "',password='"+s.getPassword() +
                "',address='"+s.getAddress() +
                "',explain='"+s.getExplain() +
                "',starPrice="+s.getStarPrice()+
                ",deliveryPrice="+s.getDeliveryPrice()+
                "where id="+s.getId();
        var resultnum=dbhelper.executeUpdate(sql);
        return resultnum>0;
    }

    @Override
    public boolean delete(int i) throws SQLException {
        var sql="delete from Business_Inf where id="+i;
        var resultnum=dbhelper.executeUpdate(sql);
        return resultnum>0;
    }

    @Override
    public Business getOne(int i) throws SQLException {
        var sql="select * from Business_Inf where id=" + i ;
        var resultset=dbhelper.executeQuery(sql);
        if(resultset.next()) {
            var business = Business.builder()
                    .id(resultset.getInt("id"))
                    .password(resultset.getString("password"))
                    .name(resultset.getString("name"))
                    .address(resultset.getString("address"))
                    .explain(resultset.getString("explain"))
                    .starPrice(resultset.getDouble("starPrice"))
                    .deliveryPrice(resultset.getDouble("deliveryPrice"))
                    .build();
//            System.out.println(business);
            return business;
        }
        else{
            return null;
        }
    }

    @Override
    public List<Business> getAll() throws SQLException {
        List<Business>  result= new ArrayList<Business>();
        String sql = "select * from Business_Inf";
        var resultset=dbhelper.executeQuery(sql);
        while(resultset.next()){
            var business = Business.builder()
                    .id(resultset.getInt("id"))
                    .password(resultset.getString("password"))
                    .name(resultset.getString("name"))
                    .address(resultset.getString("address"))
                    .explain(resultset.getString("explain"))
                    .starPrice(resultset.getDouble("starPrice"))
                    .deliveryPrice(resultset.getDouble("deliveryPrice"))
                    .build();
//            System.out.println(business);
            result.add(business);
        }
        return result;
    }

    @Override
    public List<Business> query(String nameKey, String addressKey, String explainKey) throws SQLException {
        List<Business>  result= new ArrayList<Business>();
        String sql = "select * from Business_Inf  where name like '%"+nameKey+"%' and address like '%"+addressKey+"%' and explain like '%"+explainKey+"%'";
        var resultset=dbhelper.executeQuery(sql);
        while(resultset.next()){
            var business = Business.builder()
                    .id(resultset.getInt("id"))
                    .password(resultset.getString("password"))
                    .name(resultset.getString("name"))
                    .address(resultset.getString("address"))
                    .explain(resultset.getString("explain"))
                    .starPrice(resultset.getDouble("starPrice"))
                    .deliveryPrice(resultset.getDouble("deliveryPrice"))
                    .build();
//            System.out.println(business);
            result.add(business);
        }
        return result;
    }
}
