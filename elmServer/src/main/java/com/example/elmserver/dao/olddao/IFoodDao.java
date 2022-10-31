package com.example.elmserver.dao.olddao;

import com.example.elmserver.entities.Business;
import com.example.elmserver.entities.Food;

import java.sql.SQLException;
import java.util.List;

public interface IFoodDao {

    public boolean save(Food s) throws SQLException;//保存记录的方法
    public boolean update(Food s) throws SQLException;//更新记录的方法
    public boolean delete(int i) throws SQLException;//删除记录的方法,通过主键删除
    public Food getOne(int i) throws SQLException;//获取一条记录的方法
    public List<Food> getAll() throws SQLException;//获取全部记录的方法
    public List<Food> queryFoodByBussinessId(int businessid) throws SQLException;//通过商家的编号查询菜品列表
}
