/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.model.Contact;
import java.util.List;

/**
 *
 * @author caner
 */
/**
 * The ContactDAO interface defines the methods for CRUD operations and search functionality
 * for managing contacts.
 * 
 */
public interface ContactDAO {
    
     /**
     * Adds a new contact.
     * 
     * @param contact The contact to be added.
     */
    void addContact(Contact contact);
    
    /**
     * Retrieves all contacts.
     * 
     * @return A list of all contacts.
     */
    List<Contact> getAllContacts();
    
    /**
     * Updates an existing contact.
     * 
     * @param contact The contact with updated information.
     * @return true if the contact was successfully updated, false otherwise.
     */
    boolean updateContact(Contact contact);
    
    /**
     * Deletes a contact by its ID.
     * 
     * @param contactId The ID of the contact to be deleted.
     * @return true if the contact was successfully deleted, false otherwise.
     */
    boolean deleteContact(int contactId);
    
    /**
     * Finds a contact by its ID.
     * 
     * @param id The ID of the contact to find.
     * @return The contact with the specified ID, or null if not found.
     */
    public Contact findById(int id);
    
    /**
     * Clears all contacts from the database.
     */
    public void clearAllContacts();

    /**
     * Finds contacts by their email.
     * 
     * @param email The email to search for.
     * @return A list of contacts with the specified email.
     */
    List<Contact> findByEmail(String email);
    
    /**
     * Finds contacts by their phone number.
     * 
     * @param phone The phone number to search for.
     * @return A list of contacts with the specified phone number.
     */
    List<Contact> findByPhone(String phone);
    
    /**
     * Finds contacts by their name.
     * 
     * @param name The name to search for.
     * @return A list of contacts with the specified name.
     */
    List<Contact> findByName(String name);
}
