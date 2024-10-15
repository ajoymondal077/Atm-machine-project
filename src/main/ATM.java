package main;
import db.DatabaseConnection;
import models.User;
import services.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM extends JFrame{
    private UserService userService;
    private AccountService accountService;
    private User currentUser;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;
    public ATM()
    {
        userService = new UserService();
        accountService = new AccountService();
        setTitle("ATM Machine");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        initComponents();
    }
    private void initComponents()
    {
        JLabel userLabel = new JLabel("Username: ");
        userLabel.setBounds(50,50,80,25);
        add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(150,50,165,25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50,90,80,25);
        add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(150,90,165,25);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150,130,80,25);
        add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(240,130,100,25);
        add(registerButton);

        statusLabel = new JLabel("");
        statusLabel.setBounds(50,170,300,25);
        add(statusLabel);

        loginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                login();
            }
        });
        registerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                register();
            }
        });
    }
    private void login()
    {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        currentUser = userService.loginUser(username, password);
        if(currentUser != null)
        {
            statusLabel.setText("Log in successful! welcome, "+username);
            showAccountOptions();
        }
        else{
            statusLabel.setText("Log failed! Invalid credentials");
        }
    }
    private void register()
    {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if(userService.registerUser(username, password))
        {
            statusLabel.setText("Registration successful! please log in");
        }
        else{
            statusLabel.setText("Registration failed! Try a different username");
        }
    }
    private void showAccountOptions()
    {
        JFrame accountFrame = new JFrame("Account Operations");
        accountFrame.setSize(400,300);
        accountFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        accountFrame.setLayout(null);

        JLabel balanceLabel = new JLabel("Balance: "+accountService.checkBalance(currentUser.getUserId()));
        balanceLabel.setBounds(50,50,200,25);
        accountFrame.add(balanceLabel);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(50,90,100,25);
        accountFrame.add(depositButton);

        JButton withdrawButton = new JButton("WithDraw");
        withdrawButton.setBounds(50,130,100,25);
        accountFrame.add(withdrawButton);

        JTextField amountField = new JTextField();
        amountField.setBounds(200,110,100,25);
        accountFrame.add(amountField);

        JLabel operationStatus = new JLabel("");
        operationStatus.setBounds(50,170,300,25);
        accountFrame.add(operationStatus);

        depositButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                double amount = Double.parseDouble(amountField.getText());
                if(accountService.deposit(currentUser.getUserId(), amount))
                {
                    balanceLabel.setText("Balance: "+accountService.checkBalance(currentUser.getUserId()));
                    operationStatus.setText("Deposit successful!");
                }
                else{
                    operationStatus.setText("Deposit failed!");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                double amount = Double.parseDouble(amountField.getText());
                if(accountService.withdraw(currentUser.getUserId(), amount))
                {
                    balanceLabel.setText("Balance: "+accountService.checkBalance(currentUser.getUserId()));
                    operationStatus.setText("Withdrawl successful!");
                }
                else{
                    operationStatus.setText("Insufficient balance for withdrawal.");
                }
            }
        });
        accountFrame.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->
        {
            ATM atmApp = new ATM();
            atmApp.setVisible(true);
        });
    }
}
