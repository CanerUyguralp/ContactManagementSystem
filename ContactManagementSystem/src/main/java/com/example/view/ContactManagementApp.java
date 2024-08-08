/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import com.example.controller.ContactController;
import com.example.model.Contact;

/**
 *
 * @author caner
 */
/**
 * ContactManagementApp is the main class for the Contact Management System
 * application. It provides the GUI and interacts with the ContactController to
 * manage contacts.
 */
public class ContactManagementApp {
    // Fields for the controller and GUI components

    private ContactController controller;
    private JTextField nameField, emailField, phoneField;
    private JTextArea displayArea;

    // Constructor to initialize the controller and create the UI
    public ContactManagementApp() {
        this.controller = new ContactController();
        createUI();
    }

    // Method to create the user interface
    private void createUI() {
        JFrame frame = new JFrame("Contact Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Labels and text fields for contact information
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField();

        // Buttons for various contact operations
        JButton addButton = new JButton("Add Contact");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContact();
            }
        });

        JButton updateButton = new JButton("Update Contact");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContact();
            }
        });

        JButton deleteButton = new JButton("Delete Contact");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteContact();
            }
        });

        JButton listButton = new JButton("List Contacts");
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listContacts();
            }
        });

        JButton searchButton = new JButton("Search Person");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchContact();
            }
        });

        // Text area to display contact information
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Layout setup
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameLabel)
                                .addComponent(emailLabel)
                                .addComponent(phoneLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameField)
                                .addComponent(emailField)
                                .addComponent(phoneField)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(addButton)
                                        .addComponent(updateButton)
                                        .addComponent(deleteButton)
                                        .addComponent(listButton)
                                        .addComponent(searchButton))
                                .addComponent(scrollPane))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nameLabel)
                                .addComponent(nameField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(emailLabel)
                                .addComponent(emailField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(phoneLabel)
                                .addComponent(phoneField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(addButton)
                                .addComponent(updateButton)
                                .addComponent(deleteButton)
                                .addComponent(listButton)
                                .addComponent(searchButton))
                        .addComponent(scrollPane)
        );

        frame.add(panel);
        frame.setVisible(true);
    }

    // Method to add a new contact
    private void addContact() {
        clearDispalyArea();
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        Contact contact = new Contact(name, email, phone);
        controller.addContact(contact);
        if (contact.getId() == 0) {
            displayArea.setText("Failed to add contact. Contact already exists or invalid contact information.");
        } else {
            displayArea.setText("Contact added with ID: " + contact.getId());
        }
        clearFields();
    }

    // Method to update an existing contact
    private void updateContact() {
        clearDispalyArea();
        String idStr = JOptionPane.showInputDialog("Enter contact ID to update:");
        if (idStr == null || idStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid contact ID.");
            return;
        }

        int id = Integer.parseInt(idStr);
        Contact contactToUpdate = controller.findContactById(id);
        if (contactToUpdate != null) {
            contactToUpdate.setName(nameField.getText());
            contactToUpdate.setEmail(emailField.getText());
            contactToUpdate.setPhone(phoneField.getText());

            controller.updateContact(contactToUpdate);
            displayArea.setText("Contact updated successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Contact not found.");
        }
        clearFields();
    }

    // Method to delete a contact
    private void deleteContact() {
        clearDispalyArea();
        String idStr = JOptionPane.showInputDialog("Enter contact ID to delete:");
        int id = Integer.parseInt(idStr);
        if (controller.deleteContact(id)) {
            displayArea.setText("Contact deleted successfully.");
        } else {
            displayArea.setText("Failed to delete contact.");
        }
        clearFields();
    }

    // Method to list all contacts
    private void listContacts() {
        List<Contact> contacts = controller.getAllContacts();
        StringBuilder sb = new StringBuilder();
        for (Contact contact : contacts) {
            sb.append(contact).append("\n");
        }
        displayArea.setText(sb.toString());
    }

    // Method to search for contacts by email, phone, or name
    private void searchContact() {
        clearDispalyArea();
        String[] options = {"Email", "Phone", "Name"};
        int choice = JOptionPane.showOptionDialog(null, "Search by:", "Search Person",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice < 0) {
            return; // User canceled the dialog
        }

        String searchCriteria = options[choice];
        String searchValue = JOptionPane.showInputDialog("Enter " + searchCriteria + ":");
        if (searchValue == null || searchValue.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid " + searchCriteria + ".");
            return;
        }

        List<Contact> contacts = null;
        switch (searchCriteria) {
            case "Email":
                contacts = controller.findContactsByEmail(searchValue);
                break;
            case "Phone":
                contacts = controller.findContactsByPhone(searchValue);
                break;
            case "Name":
                contacts = controller.findContactsByName(searchValue);
                break;
        }

        if (contacts == null || contacts.isEmpty()) {
            displayArea.setText("No contacts found for " + searchCriteria + ": " + searchValue);
        } else {
            StringBuilder sb = new StringBuilder();
            for (Contact contact : contacts) {
                sb.append(contact).append("\n");
            }
            displayArea.setText(sb.toString());
        }
    }

    // Method to clear the input fields
    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
    }

    // Method to clear the display area
    private void clearDispalyArea() {
        displayArea.setText("");
    }

    // Main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ContactManagementApp());
    }
}
