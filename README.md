
Developed a console-based banking management system in Java using JDBC and MySQL. The system allows users to create accounts, make transactions, and view account details through an interactive menu. 

# Bank Management System

A simple console-based Bank Management System developed in Java using JDBC and MySQL. The project allows users to perform basic banking operations such as creating an account, making transactions, and viewing account details.

##  Features

- Create new bank accounts
- Perform deposits and withdrawals
- View account details
- Real-time balance updates
- Robust error handling for invalid inputs and database issues

##  Tech Stack

- **Programming Language:** Java
- **Database:** MySQL
- **Connectivity:** JDBC

##  Database Schema

```sql
CREATE DATABASE bank_management_system;

USE bank_management_system;

CREATE TABLE accounts (
    account_number INT PRIMARY KEY,
    customer_name VARCHAR(100),
    balance DOUBLE
);

# How to Run
Clone this repository:
git clone https://github.com/your-username/Bank-Management-System.git

Set up your MySQL database using the schema above.

Update your MySQL credentials in the BankManagementSystem.java file:

private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bank_management_system";
private static final String USERNAME = "your_username";
private static final String PASSWORD = "your_password";


Compile and run the program:
javac BankManagementSystem.java
java BankManagementSystem

# Output
1. Create Account
2. Make a Transaction
3. View Account Details
4. Exit
Enter your choice:


# Folder Structure
Bank-Management-System/
│
├── BankManagementSystem.java   # Main Java file
├── README.md                   # This README
└── database.sql                # (Optional) SQL file for schema setup

# Disclaimer
This project is for educational purposes only and does not include advanced security mechanisms like authentication, encryption, or secure transaction handling.
