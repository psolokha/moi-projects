package com.moi.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionManager {

    private static DBConnectionManager instance;
    private static Connection connection;

    private DBConnectionManager(){
        instance = this;
        Properties props = PropertiesManager.getProperties();
        try {
            connection = DriverManager.getConnection(props.getProperty("url"), props.getProperty("login"), props.getProperty("password"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**Метод для использования класса как синглтона*/
    public static Connection getConnection() {
        if (instance == null) instance = new DBConnectionManager();
        return connection;
    }
}
