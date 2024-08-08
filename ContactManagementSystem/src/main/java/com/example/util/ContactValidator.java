/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.util;

import com.example.dao.ContactDAO;
import com.example.dao.ContactDAOImpl;
import com.example.model.Contact;
import java.util.List;

/**
 *
 * @author caner
 */
/**
 * Utility class for validating contact information.
 * 
 * Provides methods to validate email and phone formats, and check for uniqueness of email and phone numbers.
 * 
 */
public class ContactValidator {

    private final ContactDAO contactDAO;
    
    /**
     * Constructor initializing ContactDAO instance.
     */
    public ContactValidator() {
        this.contactDAO = new ContactDAOImpl();
    }
    
    /**
     * Validates the email format.
     * 
     * @param email The email address to validate.
     * @return true if the email format is valid, false otherwise.
     */
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        // Regular expression for basic email format validation
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
    
    /**
     * Validates the phone number format.
     * 
     * @param phone The phone number to validate.
     * @return true if the phone format is valid, false otherwise.
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null) {
            return false;
        }
        // Regular expression to validate phone number without spaces
        String regexWithoutSpaces = "^\\+?\\d{10,15}$";
        if (phone.matches(regexWithoutSpaces)) {
            return true;
        }
        // Regular expression to validate phone number with spaces
        String regexWithSpaces = "^\\+?\\d{1,3}( \\d{1,4}){1,4}$";
        return phone.matches(regexWithSpaces);
    }
    
    /**
     * Checks if the given email is unique in the contact list.
     * 
     * @param email The email address to check.
     * @return true if the email is unique, false otherwise.
     */
    public boolean isEmailUnique(String email) {
        List<Contact> contacts = contactDAO.getAllContacts();
        return contacts.stream().noneMatch(contact -> email.equals(contact.getEmail()));
    }
    
     /**
     * Checks if the given phone number is unique in the contact list.
     * 
     * @param phone The phone number to check.
     * @return true if the phone number is unique, false otherwise.
     */
    public boolean isPhoneUnique(String phone) {
        List<Contact> contacts = contactDAO.getAllContacts();
        return contacts.stream().noneMatch(contact -> phone.equals(contact.getPhone()));
    }
}
