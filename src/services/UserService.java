package services;
import java.sql.*;
import db.DatabaseConnection;
import models.User;
public class UserService {
    public boolean registerUser(String username,String password)
    {
        String sql = "Insert into users(username,password) VALUES(?,?)";
        try(Connection conn = DatabaseConnection.connect())
        {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            return true;
        }
        catch(SQLException e)
        {
            System.out.println("Registration failed: "+e.getMessage());
            return false;
        }
    }
    public User loginUser(String username,String password)
    {
        String sql = "select * from users where username = ? AND password = ?";
        try(Connection conn = DatabaseConnection.connect())
        {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                return new User(rs.getInt("user_id"),rs.getString("username") , rs.getString("password"),rs.getDouble("balance"));
            }
        }
        catch(SQLException e)
        {
            System.out.println("LogIn Failed: "+ e.getMessage());
        }
        return null;
    }
}
