package services;
import java.sql.*;
import db.DatabaseConnection;
import models.User;
public class AccountService {
    public double checkBalance(int userId)
    {
        String sql = "select balance from users where user_id = ?";
        try(Connection conn = DatabaseConnection.connect())
        {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,userId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                return rs.getDouble("balance");
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error checking balance: " + e.getMessage());
        }
        return 0.0;
    }
    public boolean deposit(int userId, double amount) {
        String sql = "UPDATE users SET balance = balance + ? WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, amount);
            pstmt.setInt(2, userId);
            pstmt.executeUpdate();
            return true; 
        } catch (SQLException e) {
            System.out.println("Deposit failed: " + e.getMessage());
            return false; 
        }
    }
    public boolean withdraw(int userId, double amount) {
        if (checkBalance(userId) >= amount) {
            String sql = "UPDATE users SET balance = balance - ? WHERE user_id = ?";
            try (Connection conn = DatabaseConnection.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setDouble(1, amount);
                pstmt.setInt(2, userId);
                pstmt.executeUpdate();
                return true; 
            } catch (SQLException e) {
                System.out.println("Withdrawal failed: " + e.getMessage());
                return false; 
            }
        }
        return false; 
    }
}
