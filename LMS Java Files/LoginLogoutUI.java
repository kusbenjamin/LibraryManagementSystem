// CMSC 495 7380
// Benjamin Kus
// Benjamin Ramos
// Brandon Durham

// REVISION HISTROY
// ---------------------------------------------------------------------------------------------------------------------------
// 1.0 Initial creation of class.										Benjamin Kus
// 1.1 Creation of java swing UI elements.								Benjamin Kus Benjamin Ramos
// 1.2 Addition of error handling screens.								Benjamin Kus Benjamin Ramos
// 1.3 Creation of database and database connection						Benjamin Kus Benjamin Ramos
// 1.4 Finalizing class, logic, and testing.							Benjamin Kus Benjamin Ramos Brandon Durham

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class LoginLogoutUI extends JFrame implements ActionListener
{
	private JButton loginButton;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JTextField userField;
    private JPasswordField passwordField;

	public LoginLogoutUI()
	{
		// Login JFrame creation and UI configuration.
		setTitle("Login");
		setBounds(1075, 610, 400, 200);
		setResizable(false);
		setLayout(null);
		
		
		// User JLabel and JTextField creation and UI configuration.
		userLabel = new JLabel("Username:");
		userLabel.setBounds(40, 15, 100, 30);
		add(userLabel);
		
		userField = new JTextField();
		userField.setBounds(120, 15, 200, 30);
		add(userField);
		
		
		// Password JLabel and JTextField creation and UI configuration.
		passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(40, 50, 100, 30);
		add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(120, 50, 200, 30);
		add(passwordField);
		
        
        // Login Button creation and UI  configuration.
        loginButton = new JButton("Login");
        loginButton.setBounds(120,100,150,30);
        add(loginButton);
        
        
        // Login Button ActionListener interaction.
        loginButton.addActionListener(this);
        
        setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		 String username = userField.getText();
		 String password = passwordField.getText();
		 
	     if (username.equals(""))
	     {
	    	 JOptionPane.showMessageDialog(null,"Please enter your username.");
	     }
	     else if (password.equals(""))
	     {
	    	 JOptionPane.showMessageDialog(null,"Please enter your password.");
	     }
	     else
	     {
	    	 Connection connection = connect();
	    	 
	    	 try
	    	 {
	    		 Statement stmt = connection.createStatement();
	    		 String st = ("SELECT * FROM `library`.`users` WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "'");
	    		 ResultSet rs = stmt.executeQuery(st);
	    		 
	    		 if (rs.next() == false)
	    		 {
	    			 JOptionPane.showMessageDialog(null, "Invalid username or password.");
	    		 }
	    		 else
	    		 {
	    			 dispose();
	    			 SystemUI adminMenu = new SystemUI();
	    		 }
	    	 }
	    	 catch (Exception ex)
	    	 {
	    		 System.out.println(ex);
	    	 }
	     }
	}
	
	public static Connection connect()
	{
		try
		{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "CMSC4957380");
			return con;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		
		return null;
	}
}
