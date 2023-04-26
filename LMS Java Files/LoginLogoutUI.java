// CMSC 495 7380
// Benjamin Kus
// Benjamin Ramos
// Brandon Durham

// REVISION HISTROY
// ---------------------------------------------------------------------------------------------------------------------------
// 1.0 Initial creation of class.										Benjamin Kus
// 1.1 Creation of Java Swing UI Elements								Benjamin Kus
// 1.2 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
		setBounds(1000, 500, 400, 200);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		// User JLabel and JTextField creation and UI configuration.
		userLabel = new JLabel("Username:");
		userLabel.setBounds(30, 15, 100, 30);
		add(userLabel);
		
		userField = new JTextField();
		userField.setBounds(110, 15, 200, 30);
		add(userField);
		
		
		// Password JLabel and JTextField creation and UI configuration.
		passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(30, 50, 100, 30);
		add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(110, 50, 200, 30);
		add(passwordField);
		
        
        // Login Button creation and UI  configuration.
        loginButton = new JButton("Login");
        loginButton.setBounds(130,120,80,25);
        add(loginButton);
        
        
        // Login Button ActionListener interaction.
        loginButton.addActionListener(this);
        
        setVisible(true);
	}
	
	public void ValidateCredentials()
	{
		// Place holder for credential validation method.
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Action listener for capturing user credentials.
		if (e.getSource() == loginButton)
		{
			System.out.println("\n\nLogging in with the following credentials:\nUsername: " + userField.getText() + "\nPassword: " + passwordField.getText());
		}
	}
}
