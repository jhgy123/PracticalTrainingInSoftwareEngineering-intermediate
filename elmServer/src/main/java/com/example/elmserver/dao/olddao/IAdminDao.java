package com.example.elmserver.dao.olddao;

import com.example.elmserver.entities.Admin;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface IAdminDao {
    public boolean save(Admin s) throws SQLException;//保存记录的方法
    public boolean update(Admin s) throws SQLException;//更新记录的方法
    public boolean delete(int i) throws SQLException;//删除记录的方法,通过主键删除
    public Admin getOne(int i) throws SQLException;//获取一条记录的方法
    public List<Admin> getAll() throws SQLException;//获取全部记录的方法
}
