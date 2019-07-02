package com.qty.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private String USERNAME="root";
    private String DRIVER="com.mysql.cj.jdbc.Driver";
    private String PWD="root";
    private String URL = "jdbc:mysql://127.0.0.1:3306/user?useUnicode=true&&characterEncoding=utf-8&&serverTimezone=UTC";
    private Connection connection;
    public DBConnection(){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,USERNAME,PWD);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test_connection(){
        DBConnection db = new DBConnection();
        System.out.println(db.connection == null);
    }
    public Connection getConnection(){
        return connection;
    }
}
