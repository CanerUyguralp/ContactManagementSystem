/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

/**
 *
 * @author caner
 */
/**
 * Represents a contact with details such as ID, name, phone, and email.
 * Provides getters and setters for each field.
 * 
 */
public class Contact {

    private int id;         // Unique identifier for the contact
    private String name;    // Name of the contact
    private String phone;   // Phone number of the contact
    private String email;   // Email address of the contact

    public Contact() {
        // Default constructor
    }
    
    /**
     * Parameterized constructor to initialize a contact with name, email, and phone.
     * 
     * @param name  The name of the contact.
     * @param email The email address of the contact.
     * @param phone The phone number of the contact.
     */
    public Contact(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Getters and Setters for fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Contact{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", phone='" + phone + '\''
                + ", email='" + email + '\''
                + '}';
    }
}
