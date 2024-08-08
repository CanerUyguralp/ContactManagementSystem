/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.test;

import com.example.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author caner
 */
/**
 * Test class to verify the connection to the database using DatabaseUtil.
 * 
 */
public class DatabaseTest {
    public static void main(String[] args) {
        // Attempt to establish a connection to the database
        try (Connection connection = DatabaseUtil.getConnection()) {
            // Check if the connection was successful
            if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            // Print the stack trace if there is an SQLException
            e.printStackTrace();
        }
    }
}
