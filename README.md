# Library Management System

This is a Java-based Library Management System that provides basic functionalities for managing books and users in a library. It offers two types of users: **Admins** and **Regular Users**, with role-based access to different features.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
  - [Admin Menu](#admin-menu)
  - [User Menu](#user-menu)
- [Inspiration](#inspiration)

## Features
- **Admin Menu:**
  - Login as an admin.
  - Add new books to the library.
  - View all books in the library.
  - View details of books that are currently issued.

- **User Menu:**
  - Login as a user.
  - View available books.
  - Issue books.
  - Return books.
  
- **MySQL Integration**: All data is persisted using MySQL for efficient management of users and books.

## Technologies Used
- **Java**: Core logic and object-oriented design.
- **JDBC (Java Database Connectivity)**: For communication with the MySQL database.
- **MySQL**: Used as the backend relational database to store book and user information.

## Installation

1. **Clone the repository**:
   ```bash
   
   git clone https://github.com/venuka04/library-management-system.git
   ```

2.  Set up the MySQL database:
    - Import the library.sql file to set up the database structure.
    - Update the database credentials in the Connect.java file.
  
3. Compile the Java files:
   ```bash
   
   javac *.java
   ```

4. Run the program:
   ```bash
   
   java Main
   ```

## Usage

### Admin Menu:
- Login using your admin credentials.
- Add new books to the library.
- View details of all available books.
- View the list of issued books along with user details.

### User Menu:
- Login using your user credentials.
- Browse available books.
- Issue a book by selecting the book ID.
- Return a book using the return option.


## Database Setup
The MySQL database structure includes the following tables:

- Users: Stores user information.
- Books: Stores book details.
- IssuedBooks: Tracks which books have been issued to which users.

Updating the database connection details in Connect.java before running the project is necessary.

## Inspiration
This project was inspired by an Edureka Library Management System project. Special thanks to Edureka for providing the foundation and insights for building this system.
