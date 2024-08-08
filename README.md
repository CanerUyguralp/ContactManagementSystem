Contact Management System


Overview

The Contact Management System is a Java-based desktop application designed to manage a list of contacts. It provides functionalities to add, update, delete, and search contacts by various criteria such as name, email, and phone number. The application interacts with a MySQL database to store and retrieve contact information.


Features:

    *Add Contacts: Add new contacts with a name, email, and phone number.
    *Update Contacts: Modify existing contact details.
    *Delete Contacts: Remove contacts from the database.
    *Search Contacts: Search for contacts by name, email, or phone number.
    *Database Integration: Stores contact information in a MySQL database.
    *Validation: Ensures that email addresses and phone numbers are valid and unique.


Project Structure

├── src/
│   ├── com/
│   │   └── example/
│   │       ├── controller/
│   │       │   └── ContactController.java
│   │       ├── dao/
│   │       │   ├── ContactDAO.java
│   │       │   └── ContactDAOImpl.java
│   │       ├── model/
│   │       │   └── Contact.java
│   │       ├── util/
│   │       │   ├── ContactValidator.java
│   │       │   └── DatabaseUtil.java
│   │       ├── view/
│   │       │   └── MainApplication.java
│   │       └── test/
│   │           └── DatabaseTest.java
├── resources/
│   ├── logging.properties
└── README.md


*controller: Contains the ContactController class, which acts as a mediator between the view and the data access objects (DAO).
*dao: Contains the ContactDAO interface and its implementation ContactDAOImpl, responsible for database operations.
*model: Contains the Contact class, which represents a contact entity.
*util: Contains utility classes like DatabaseUtil for managing database connections and ContactValidator for validating contact data.
*view: Contains the MainApplication class, which is the entry point of the application.
*test: Contains a simple DatabaseTest class to verify the database connection.


Requirements:

    *Java 8 or later
    *MySQL 5.7 or later
    *Maven (for dependency management)
    *Apache NetBeans (recommended for development)

Setup Instructions

1. Clone the Repository

git clone https://github.com/your-username/contact-management-system.git
cd contact-management-system

2. Database Configuration

    *Create a MySQL database named contact_db.

    *Update the database configuration in DatabaseUtil.java with your MySQL credentials:

	private static final String URL = "jdbc:mysql://localhost:3306/contact_db";
	private static final String USER = "root";
	private static final String PASSWORD = "your-password";

    *Import the SQL script (if any) to create the necessary table(s):

	CREATE TABLE contacts (
    		id INT AUTO_INCREMENT PRIMARY KEY,
    		name VARCHAR(255) NOT NULL,
    		email VARCHAR(255) NOT NULL UNIQUE,
    		phone VARCHAR(20) NOT NULL UNIQUE
	);

3. Build the Project

Use Maven to build the project:

mvn clean install


4. Run the Application

Navigate to the 'view' package and run the 'MainApplication.java' class to start the application.

java -cp target/contact-management-system-1.0-SNAPSHOT.jar com.example.view.MainApplication


5. Test the Database Connection

You can run the 'DatabaseTest.java' class to verify that the application can connect to the MySQL database successfully.


Logging

The application uses Java's built-in logging framework. Logging configuration can be found in the resources/logging.properties file. You can customize this file to adjust the logging levels and output formats.


Contributing

If you'd like to contribute to the project, feel free to fork the repository and submit a pull request.


License

This project is licensed under the MIT License.


Contact

Author: Caner
Email: caneruyguralp@windowslive.com



