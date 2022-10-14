package com.example.elmserver.dao.impl;

import com.example.elmserver.utils.DBHelper;
import com.example.elmserver.dao.IFoodDao;
import com.example.elmserver.entities.Food;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FoodDao implements IFoodDao{

    @Resource
    DBHelper dbhelper;


    @Override
    public boolean save(Food s) throws SQLException {
        var sql="insert into Food_Inf(foodExplain,foodName,foodPrice,business_id) "+
                "values('"+s.getFoodExplain()+"','"+s.getFoodName()+"',"+s.getFoodPrice()+","+s.getBusiness().getId()
                +")";
        var resultnum=dbhelper.executeUpdate(sql);
        return resultnum>0;

    }

    @Override
    public boolean update(Food s) throws SQLException {
        var sql="update Food_Inf set foodName='"+s.getFoodName()+
                "',foodExplain='"+s.getFoodExplain() +
                "',foodPrice="+s.getFoodPrice()+
                ",business_id="+s.getBusiness().getId()+
                "where foodId="+s.getFoodId();
        var resultnum=dbhelper.executeUpdate(sql);
        return resultnum>0;
    }

    @Override
    public boolean delete(int i) throws SQLException {
        var sql="delete from Food_Inf where foodId="+i;
        var resultnum=dbhelper.executeUpdate(sql);
        return resultnum>0;
    }

    @Override
    public Food getOne(int i) throws SQLException {
        BusinessDao businessDao =new BusinessDao();
        var sql="select * from Food_Inf where foodId=" + i ;
        var resultset=dbhelper.executeQuery(sql);
        if(resultset.next()) {
            var food = Food.builder()
                    .foodId(resultset.getInt("foodId"))
                    .foodName(resultset.getString("foodName"))
                    .foodExplain(resultset.getString("foodExplain"))
                    .foodPrice(resultset.getDouble("foodPrice"))
                    .business(businessDao.getOne(resultset.getInt("business_id")))
                    .build();
//            System.out.println(food);
//            System.out.println(businessDao.getOne(resultset.getInt("business_id")));
            return food;
        }
        else{
            return null;
        }
    }

    @Override
    public List<Food> getAll() throws SQLException {
        BusinessDao businessDao =new BusinessDao();
        List<Food>  result= new ArrayList<Food>();
        String sql = "select * from Food_Inf";
        var resultset=dbhelper.executeQuery(sql);
        while(resultset.next()){
            var food = Food.builder()
                    .foodId(resultset.getInt("foodId"))
                    .foodName(resultset.getString("foodName"))
                    .foodExplain(resultset.getString("foodExplain"))
                    .foodPrice(resultset.getDouble("foodPrice"))
                    .business(businessDao.getOne(resultset.getInt("business_id")))
                    .build();
            System.out.println(food);
//            System.out.println(businessDao.getOne(resultset.getInt("business_id")));
            result.add(food);
        }
        return result;
    }

    @Override
    public List<Food> queryFoodByBussinessId(int businessid) throws SQLException {
        BusinessDao businessDao =new BusinessDao();
        List<Food>  result= new ArrayList<Food>();
        String sql = "select * from Food_Inf  where business_id="+businessid;
        var resultset=dbhelper.executeQuery(sql);
        while(resultset.next()){
            var food = Food.builder()
                    .foodId(resultset.getInt("foodId"))
                    .foodName(resultset.getString("foodName"))
                    .foodExplain(resultset.getString("foodExplain"))
                    .foodPrice(resultset.getDouble("foodPrice"))
                    .business(businessDao.getOne(resultset.getInt("business_id")))
                    .build();
            System.out.println(food);
//            System.out.println(businessDao.getOne(resultset.getInt("business_id")));
            result.add(food);
        }
        return result;
    }
}
