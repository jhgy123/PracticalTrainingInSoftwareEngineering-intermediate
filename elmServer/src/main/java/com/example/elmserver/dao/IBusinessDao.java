package com.example.elmserver.dao;

import com.example.elmserver.entities.Business;

import java.sql.SQLException;
import java.util.List;

public interface IBusinessDao {

    public boolean save(Business s) throws SQLException;//保存记录的方法
    public boolean update(Business s) throws SQLException;//更新记录的方法
    public boolean delete(int i) throws SQLException;//删除记录的方法,通过主键删除
    public Business getOne(int i) throws SQLException;//获取一条记录的方法
    public List<Business> getAll() throws SQLException;//获取全部记录的方法
    public List<Business> query(String nameKey, String addressKey, String explainKey) throws SQLException;//根据商家的名称关键字、地址关键字、介绍关键字模糊查询
}
