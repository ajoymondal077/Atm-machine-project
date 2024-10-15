package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/atm_machine";
    private static final String USER = "root";
    private static final String PASSWORD = "Ajoy@678";
    public static Connection connect(){
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        }
        catch(SQLException e)
        {
            System.out.println("Connection failed: "+e.getMessage());
        }
        return connection;
    }
}
