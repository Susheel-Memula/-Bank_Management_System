import java.sql.*;
import java.util.Scanner;

public class BankManagementSystem {

    // JDBC URL, username, and password of MySQL server 
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bank_management_system";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Gcet@123";

    // JDBC variables for opening, closing and managing connection
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void main(String[] args) {
        try {
            // Connect to the database
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();

            // Create a Scanner object for user input
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. Create Account");
                System.out.println("2. Make a Transaction");
                System.out.println("3. View Account Details");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        createAccount(scanner);
                        break;
                    case 2:
                        makeTransaction(scanner);
                        break;
                    case 3:
                        viewAccountDetails(scanner);
                        break;
                    case 4:
                        System.out.println("Exiting the program.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createAccount(Scanner scanner) throws SQLException {
        System.out.print("Enter customer name: ");
        String customerName = scanner.next();

        // Generate a random account number for simplicity
        int accountNumber = (int) (Math.random() * 10000);

        double initialBalance = 0.0; // Initial balance is set to 0

        // Insert the new account into the database
        String insertQuery = "INSERT INTO accounts VALUES (" + accountNumber + ", '" + customerName + "', " + initialBalance + ")";
        statement.executeUpdate(insertQuery);

        System.out.println("Account created successfully. Account Number: " + accountNumber);
    }

    private static void makeTransaction(Scanner scanner) throws SQLException {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();

        // Check if the account exists
        String checkAccountQuery = "SELECT * FROM accounts WHERE account_number = " + accountNumber;
        resultSet = statement.executeQuery(checkAccountQuery);

        if (!resultSet.next()) {
            System.out.println("Account does not exist.");
            return;
        }

        System.out.print("Enter transaction amount: ");
        double transactionAmount = scanner.nextDouble();

        // Update the balance
        String updateQuery = "UPDATE accounts SET balance = balance + " + transactionAmount + " WHERE account_number = " + accountNumber;
        statement.executeUpdate(updateQuery);

        System.out.println("Transaction successful. Updated balance: " + getBalance(accountNumber));
    }

    private static void viewAccountDetails(Scanner scanner) throws SQLException {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();

        // Check if the account exists
        String checkAccountQuery = "SELECT * FROM accounts WHERE account_number = " + accountNumber;
        resultSet = statement.executeQuery(checkAccountQuery);

        if (!resultSet.next()) {
            System.out.println("Account does not exist.");
            return;
        }

        // Display account details
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Customer Name: " + resultSet.getString("customer_name"));
        System.out.println("Balance: " + resultSet.getDouble("balance"));
    }

    private static double getBalance(int accountNumber) throws SQLException {
        // Retrieve and return the current balance
        String balanceQuery = "SELECT balance FROM accounts WHERE account_number = " + accountNumber;
        resultSet = statement.executeQuery(balanceQuery);
        resultSet.next();
        return resultSet.getDouble("balance");
    }
}
