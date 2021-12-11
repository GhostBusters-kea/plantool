package com.example.plantool.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnector {

    private static String url;
    private static String username;
    private static String password;
    private static Connection conn;

    public static Connection getConnection() {

        if (conn != null) {
            return conn;
        }

        url = System.getenv("pt_url");
        password = System.getenv("pt_password");
        username = System.getenv("pt_username");

        try {

            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
