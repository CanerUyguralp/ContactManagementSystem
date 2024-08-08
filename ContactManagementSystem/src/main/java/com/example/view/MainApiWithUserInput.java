/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.view;

/**
 *
 * @author caner
 */
import com.example.dao.ContactDAO;
import com.example.dao.ContactDAOImpl;
import com.example.model.Contact;
import java.util.List;
import java.util.Scanner;

/**
 * MainApiWithUserInput is the main class for a command-line based Contact Management System.
 * It provides a menu-driven interface for the user to manage contacts.
 */
public class MainApiWithUserInput {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);// Scanner for user input
        ContactDAO contactDAO = new ContactDAOImpl();// DAO for contact operations
        
        // Main loop for the command-line interface
        while (true) {
            // Display menu options
            System.out.println("1. Add Contact");
            System.out.println("2. List Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            
            // Read user choice
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            
             // Perform action based on user choice
            switch (choice) {
                case 1:
                 // Add a new contact
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter phone: ");
                    String phone = scanner.nextLine();
                    Contact contact = new Contact(name, email, phone);
                    contactDAO.addContact(contact);
                    System.out.println("Contact added with ID: " + contact.getId());
                    break;
                case 2:
                // List all contacts
                    List<Contact> contacts = contactDAO.getAllContacts();
                    for (Contact c : contacts) {
                        System.out.println(c);
                    }
                    break;
                case 3:
                // Update an existing contact
                    System.out.print("Enter contact ID to update: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    Contact contactToUpdate = contactDAO.findById(idToUpdate);
                    if (contactToUpdate != null) {
                        System.out.print("Enter new name: ");
                        contactToUpdate.setName(scanner.nextLine());
                        System.out.print("Enter new email: ");
                        contactToUpdate.setEmail(scanner.nextLine());
                        System.out.print("Enter new phone: ");
                        contactToUpdate.setPhone(scanner.nextLine());
                        contactDAO.updateContact(contactToUpdate);
                        System.out.println("Contact updated successfully.");
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 4:
                // Delete a contact
                    System.out.print("Enter contact ID to delete: ");
                    int idToDelete = scanner.nextInt();
                    if (contactDAO.deleteContact(idToDelete)) {
                        System.out.println("Contact deleted successfully.");
                    } else {
                        System.out.println("Failed to delete contact.");
                    }
                    break;
                case 5:
                // Exit the application
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                // Handle invalid choice
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
