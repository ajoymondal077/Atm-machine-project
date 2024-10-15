package models;

public class User {
    private int userId;
    private String username;
    private String password;
    private double balance;
    public User(int userId,String username,String password,double balance)
    {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }
    public int getUserId()
    {
        return userId;
    }
    public String getUserName()
    {
        return username;
    }
    public double getBalance()
    {
        return balance;
    }
    public void setBalance(double balance)
    {
        this.balance = balance;
    }
}
