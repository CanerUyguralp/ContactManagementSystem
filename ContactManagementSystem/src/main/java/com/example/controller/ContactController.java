/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.dao.ContactDAO;
import com.example.dao.ContactDAOImpl;
import com.example.model.Contact;
import java.util.List;

/**
 *
 * @author caner
 */

/**
 * The ContactController class provides methods for managing contacts.
 * It serves as an intermediary between the view and the data access layer.
 * 
 */
public class ContactController {

    private ContactDAO contactDAO;
    
    // Constructor initializes the ContactDAO implementation
    public ContactController() {
        this.contactDAO = new ContactDAOImpl();
    }

    /**
     * Adds a new contact.
     * 
     * @param contact The contact to be added.
     */
    public void addContact(Contact contact) {
        contactDAO.addContact(contact);
    }

    /**
     * Updates an existing contact.
     * 
     * @param contact The contact with updated information.
     */
    public void updateContact(Contact contact) {
        contactDAO.updateContact(contact);
    }

    /**
     * Deletes a contact by its ID.
     * 
     * @param id The ID of the contact to be deleted.
     * @return true if the contact was successfully deleted, false otherwise.
     */
    public boolean deleteContact(int id) {
        return contactDAO.deleteContact(id);
    }

    /**
     * Retrieves all contacts.
     * 
     * @return A list of all contacts.
     */
    public List<Contact> getAllContacts() {
        return contactDAO.getAllContacts();
    }

    /**
     * Finds a contact by its ID.
     * 
     * @param id The ID of the contact to find.
     * @return The contact with the specified ID, or null if not found.
     */
    public Contact findContactById(int id) {
        return contactDAO.findById(id);
    }

    /**
     * Finds contacts by their email.
     * 
     * @param email The email to search for.
     * @return A list of contacts with the specified email.
     */
    public List<Contact> findContactsByEmail(String email) {
        return contactDAO.findByEmail(email);
    }

    /**
     * Finds contacts by their phone number.
     * 
     * @param phone The phone number to search for.
     * @return A list of contacts with the specified phone number.
     */
    public List<Contact> findContactsByPhone(String phone) {
        return contactDAO.findByPhone(phone);
    }

    /**
     * Finds contacts by their name.
     * 
     * @param name The name to search for.
     * @return A list of contacts with the specified name.
     */
    public List<Contact> findContactsByName(String name) {
        return contactDAO.findByName(name);
    }
}
