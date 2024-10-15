# ATM Machine Project (Java, MySQL)

This project simulates a basic **ATM System** using Java with MySQL integration. It allows users to register, log in, and perform account operations such as balance checking, depositing, and withdrawing money.

## Features:
- **User Registration & Login**
- **Check Account Balance**
- **Deposit Money**
- **Withdraw Money**
- **Error Handling** for invalid operations

## Technologies Used:
- **Java** for business logic
- **Swing** for GUI interface
- **MySQL** for storing user and account details

## Screen Recording:
Here is a video demonstration of the ATM system:

[ATM Machine Demo](./images/atm_demo.mp4)

## How to Run:
1. Clone the repository.
2. Set up the MySQL database using the `atm_machine.sql` script located in the `database/` folder.
3. Make sure to update the database connection details in the `DatabaseConnection.java` file.
4. Compile and run the Java files.

```bash
# Compile all classes
javac -cp "path_to_mysql_connector.jar" src/main/ATM.java

# Run the ATM application
java -cp "path_to_mysql_connector.jar:." src/main/ATM
