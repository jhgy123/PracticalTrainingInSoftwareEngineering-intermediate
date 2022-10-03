package com.example.elmserver.dao;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库帮助类
 */
@Component
public final class DBHelper {

    static String _connStr = "jdbc:sqlserver://127.0.0.1:11069;DatabaseName=Software_Practical_Training;TrustServerCertificate=true;user=sa;password=180714;";

    public static String getConnectionString() {
        return _connStr;
    }

    public static void setConnectionString(String connStr) {
        DBHelper._connStr = connStr;
    }

    public static Connection getConnection() throws SQLException {
        var conStr = getConnectionString();
        return getConnection(conStr);
    }

    /**
     * 获取数据库连接
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection(String connStr) throws SQLException {
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
    public static ResultSet executeQuery(String sql) throws SQLException {
        //通过连接字符串打开一个特定数据库的连接
        var con = DBHelper.getConnection();

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
    public static int executeUpdate(String sql) throws SQLException {
        //通过连接字符串打开一个特定数据库的连接
        var con = DBHelper.getConnection();

        //通过连接创建一个语句sql执行器
        var stm = con.createStatement();
        var result = stm.executeUpdate(sql);

        return result;
    }
}
