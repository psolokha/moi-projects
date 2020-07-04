package com.moi.requests;

import com.moi.utils.DBConnectionManager;
import java.sql.*;
import java.util.*;

public class SQLApplication {
    private static SQLApplication instance;
    private static Connection connection;

    private SQLApplication() {
        instance = this;
        connection = DBConnectionManager.getConnection();
    }

    /** Метод для использования класса как синглтона */
    public static SQLApplication getInstance() {
        if (instance == null) instance = new SQLApplication();
            return instance;
    }

    /** Метод, который отправляет в базу данных запрос, а возвращает данные в видел List'a Map'ов <String,String> */
    public List<Map<String, String>> executeQuery(String sqlQuery){
        Statement stmt = null;
        ResultSet rs = null;
        List<Map<String, String>> results = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sqlQuery);
            while (rs.next()) {
                Map<String, String> resultsTmp = new LinkedHashMap<>();
                ResultSetMetaData rsmd = rs.getMetaData();
                int numColumns = rsmd.getColumnCount();
                for (int i = 1; i < numColumns + 1; i++) {
                    try{
                        resultsTmp.put(rsmd.getColumnName(i), rs.getString(rsmd.getColumnLabel(i)));
                    } catch (SQLException e) {
                        resultsTmp.put(rsmd.getColumnName(i), null);
                    }
                }
                results.add(resultsTmp);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            closeResultSet(rs);
            closeStatement(stmt);
        }
        printer(results);
        return results;
    }

    /** Метод, который отправляет в базу запрос на создание/изменение данных
     * и возвращает кол-во изменных строк */
    public Integer updateExecute(String query) {
        Statement stmt = null;
        Integer strNum = null;
        try {
            stmt = connection.createStatement();
            strNum = stmt.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeStatement(stmt);
        }
        System.out.println("Кол-во обработаных строк: " + strNum);
        return strNum;
    };

    /** Метод, который выводит на экран результат SQL запроса */
    private void printer(List<Map<String, String>> resultSet) {
        for (Map<String, String> field : resultSet) {
            field.entrySet().forEach(entry -> System.out.print(entry.getKey() + ": " + entry.getValue() + '\t'));
            System.out.println('\n');
        }
    }

    /** Метод, закрывающий Statement */
    private void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /** Метод, закрывающий ResultSet */
    private void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
