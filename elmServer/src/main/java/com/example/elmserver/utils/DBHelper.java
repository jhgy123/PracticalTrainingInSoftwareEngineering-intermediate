package com.example.elmserver.utils;

import com.example.elmserver.configuration.properies.MySecurityProperites;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库帮助类
 */

@Component
public final class DBHelper {

    @Resource
    MySecurityProperites secPps;

//    String _connStr = "jdbc:sqlserver://127.0.0.1:11069;DatabaseName=Software_Practical_Training;TrustServerCertificate=true;user=sa;password=180714;";

//    String _connStr =secPps.getDburl();
//    public void set_connStr(String _connStr) {
//        this._connStr = _connStr;
//    }
//    public String getConnectionString() {
//        return _connStr;
//    }
//
//    public void setConnectionString(String connStr) {
//        _connStr = connStr;
//    }

    public Connection getConnection() throws SQLException {
//        String conStr = getConnectionString();
        String conStr =secPps.getDburl();
        return getConnection(conStr);
    }

    /**
     * 获取数据库连接
     *
     * @return
     * @throws SQLException
     */
    public Connection getConnection(String connStr) throws SQLException {
        //通过连接字符串打开一个特定数据库的连接
        String connectionUrl = connStr;
        Connection con = DriverManager.getConnection(connectionUrl);

        return con;
    }

    /**
     * @param sql
     * @return
     * @throws SQLException
     */
    public ResultSet executeQuery(String sql) throws SQLException {
        //通过连接字符串打开一个特定数据库的连接
        Connection con = this.getConnection();

        //通过连接创建一个语句sql执行器
        var stm = con.createStatement();
        var result = stm.executeQuery(sql);
        return result;
    }


    /**
     * 
     * @param sql
     * @return
     * @throws SQLException
     */
    public int executeUpdate(String sql) throws SQLException {
        //通过连接字符串打开一个特定数据库的连接
        var con = this.getConnection();

        //通过连接创建一个语句sql执行器
        var stm = con.createStatement();
        var result = stm.executeUpdate(sql);

        return result;
    }
}
