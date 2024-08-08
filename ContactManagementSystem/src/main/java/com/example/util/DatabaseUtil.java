package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author caner
 */
/**
 * Utility class for managing database connections.
 * 
 * Provides a method to establish a connection to the MySQL database using the provided URL, username, and password.
 * 
 */
public class DatabaseUtil {
    
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:****/****";// server URL
    private static final String USER = "username";  // Database username
    private static final String PASSWORD = "password";  // Database password
    
    /**
     * Establishes and returns a connection to the database.
     * 
     * @return A Connection object for the database.
     * @throws SQLException If there is an error while connecting to the database.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
