/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.view;

import com.example.dao.ContactDAO;
import com.example.dao.ContactDAOImpl;
import com.example.model.Contact;
import java.io.FileInputStream;
import java.io.InputStream;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *
 * @author caner
 */
public class MainApplication {

    private static final Logger LOGGER = Logger.getLogger(MainApplication.class.getName());

    public static void main(String[] args) {

        // Load logging properties from the classpath
        try (InputStream stream = MainApplication.class.getClassLoader().getResourceAsStream("logging.properties")) {
            if (stream != null) {
                LogManager.getLogManager().readConfiguration(stream);
            } else {
                LOGGER.severe("Could not find logging.properties file");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Could not load logging properties: {0}", e.getMessage());
            // Optionally, log the stack trace for more details
            LOGGER.log(Level.SEVERE, "Exception occurred", e);
        }

        LOGGER.info("Application started");

        // Example usage of ContactDAOImpl
        ContactDAO contactDAO = new ContactDAOImpl();

        // Clear all contacts (optional, before testing)
        contactDAO.clearAllContacts();

        // Add a new contact
        Contact newContact1 = new Contact("John Doe1", "john.doe1@example.com", "+123456789");
        contactDAO.addContact(newContact1);
        //System.out.println("New contact added with ID: " + newContact1.getId());

        // Add another new contact
        Contact newContact2 = new Contact("John Doe2", "john2.doe@example.com", "+223456789");
        contactDAO.addContact(newContact2);
        //System.out.println("New contact added with ID: " + newContact2.getId());

        // Add yet another new contact
        Contact newContact3 = new Contact("John Doe3", "john3.doe@example.com", "+323456789");
        contactDAO.addContact(newContact3);
        //System.out.println("New contact added with ID: " + newContact3.getId());

        // Retrieve all contacts
        List<Contact> contacts = contactDAO.getAllContacts();
        for (Contact contact : contacts) {
            System.out.println(contact);
        }

        // Update a contact
        Contact existingContact = contactDAO.findById(1);
        if (existingContact != null) {
            existingContact.setName("Updated Name");
            existingContact.setEmail("updated.email@example.com");
            contactDAO.updateContact(existingContact);
            System.out.println("Contact updated successfully.");
        } else {
            System.out.println("Contact not found.");
        }

        // Delete a contact
        int contactIdToDelete = 2;
        if (contactDAO.deleteContact(contactIdToDelete)) {
            System.out.println("Contact with ID " + contactIdToDelete + " deleted successfully.");
        } else {
            System.out.println("Failed to delete contact with ID " + contactIdToDelete);
        }
    }
}
