/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.model.Contact;
import com.example.util.ContactValidator;
import com.example.util.DatabaseUtil;
import static com.example.util.DatabaseUtil.getConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author caner
 */
/**
 * Implementation of the ContactDAO interface using JDBC for MySQL database operations.
 * 
 * Author: Caner
 */
public class ContactDAOImpl implements ContactDAO {

    private static final Logger LOGGER = Logger.getLogger(ContactDAOImpl.class.getName());
    
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/contact_db";
    private static final String USER = "root";
    private static final String PASSWORD = "wqx0*HGJ";

    // SQL queries
    private static final String SQL_FIND_ALL = "SELECT * FROM contacts";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM contacts WHERE id=?";
    private static final String SQL_ADD_CONTACT = "INSERT INTO contacts (name, email, phone) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE contacts SET name=?, email=?, phone=? WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM contacts WHERE id=?";
    private static final String SQL_FIND_BY_EMAIL = "SELECT * FROM contacts WHERE email=?";
    private static final String SQL_FIND_BY_PHONE = "SELECT * FROM contacts WHERE phone=?";
    private static final String SQL_FIND_BY_NAME = "SELECT * FROM contacts WHERE name LIKE ?";
    
     /**
     * Establishes and returns a connection to the database.
     * 
     * @return The database connection.
     * @throws SQLException If an error occurs while establishing the connection.
     */
    protected Connection getConnection() throws SQLException {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create and return a connection
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }
    
    /**
     * Returns an instance of the ContactValidator.
     * 
     * @return The ContactValidator instance.
     */
    protected ContactValidator getContactValidator() {
        return new ContactValidator();
    }

    @Override
    public void addContact(Contact contact) {
        ContactValidator validator = getContactValidator();
        
        // Validate email format
        if (!validator.isValidEmail(contact.getEmail())) {
            LOGGER.log(Level.WARNING, "Invalid email format: {0}", contact.getEmail());
            return;
        }
        
        // Validate phone format
        if (!validator.isValidPhone(contact.getPhone())) {
            LOGGER.log(Level.WARNING, "Invalid phone format: {0}", contact.getPhone());
            return;
        }
        
        // Check for email uniqueness
        if (!validator.isEmailUnique(contact.getEmail())) {
            LOGGER.log(Level.WARNING, "Duplicate email found: {0}", contact.getEmail());
            return;
        }
        
        // Check for phone uniqueness
        if (!validator.isPhoneUnique(contact.getPhone())) {
            LOGGER.log(Level.WARNING, "Duplicate phone number found: {0}", contact.getPhone());
            return;
        }
        
        // Insert contact into the database
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_ADD_CONTACT, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, contact.getName());
            stmt.setString(2, contact.getEmail());
            stmt.setString(3, contact.getPhone());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        contact.setId(generatedKeys.getInt(1));
                        LOGGER.log(Level.INFO, "New contact added with ID: {0}", contact.getId());
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding contact", e);
        }
    }

    @Override
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        
        // Retrieve all contacts from the database
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Contact contact = mapResultSetToContact(resultSet);
                contacts.add(contact);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all contacts", e);
        }
        return contacts;
    }

    @Override
    public Contact findById(int id) {
        
        // Find a contact by its ID
        Contact contact = null;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    contact = mapResultSetToContact(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error finding contact by ID", e);
        }
        return contact;
    }

    @Override
    public boolean updateContact(Contact contact) {
        
        // Validate email format
        if (!ContactValidator.isValidEmail(contact.getEmail())) {
            LOGGER.log(Level.WARNING, "Invalid email format: {0}", contact.getEmail());
            return false;
        }
        
        // Validate phone format
        if (!ContactValidator.isValidPhone(contact.getPhone())) {
            LOGGER.log(Level.WARNING, "Invalid phone format: {0}", contact.getPhone());
            return false;
        }
        
        // Update the contact in the database
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, contact.getName());
            statement.setString(2, contact.getEmail());
            statement.setString(3, contact.getPhone());
            statement.setInt(4, contact.getId());

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating contact", e);
            return false;
        }
    }

    @Override
    public boolean deleteContact(int id) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_DELETE)) {
            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting contact", e);
            return false;
        }
    }

    @Override
    public void clearAllContacts() {
        // Delete all contacts from the database
        String query = "DELETE FROM contacts";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.executeUpdate();
            LOGGER.log(Level.INFO, "All contacts deleted successfully.");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error clearing all contacts", e);
        }
    }

    @Override
    public List<Contact> findByEmail(String email) {
        List<Contact> contacts = new ArrayList<>();
        
        // Find contacts by email
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_EMAIL)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    contacts.add(mapResultSetToContact(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error finding contacts by email", e);
        }
        return contacts;
    }

    @Override
    public List<Contact> findByPhone(String phone) {
        List<Contact> contacts = new ArrayList<>();
        
        // Find contacts by phone
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_PHONE)) {
            statement.setString(1, phone);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    contacts.add(mapResultSetToContact(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error finding contacts by phone", e);
        }
        return contacts;
    }

    @Override
    public List<Contact> findByName(String name) {
        List<Contact> contacts = new ArrayList<>();
        
        // Find contacts by name (using a LIKE query for partial matches)
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_NAME)) {
            statement.setString(1, "%" + name + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    contacts.add(mapResultSetToContact(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error finding contacts by name", e);
        }
        return contacts;
    }
    
    /**
     * Maps a ResultSet row to a Contact object.
     * 
     * @param resultSet The ResultSet from the SQL query.
     * @return The Contact object mapped from the ResultSet.
     * @throws SQLException If an error occurs while accessing the ResultSet.
     */
    private Contact mapResultSetToContact(ResultSet resultSet) throws SQLException {
        Contact contact = new Contact();
        contact.setId(resultSet.getInt("id"));
        contact.setName(resultSet.getString("name"));
        contact.setEmail(resultSet.getString("email"));
        contact.setPhone(resultSet.getString("phone"));
        return contact;
    }
}
