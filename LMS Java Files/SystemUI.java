// CMSC 495 7380
// Benjamin Kus
// Benjamin Ramos
// Brandon Durham

// REVISION HISTROY
// ---------------------------------------------------------------------------------------------------------------------------
// 1.0 Initial creation of class.										Benjamin Kus Benjamin Ramos
// 1.1 Creation of java swing UI elements. 								Benjamin Kus Benjamin Ramos
// 1.2 Addition of error handling screens.								Benjamin Kus Benjamin Ramos
// 1.3 Finalizing class, logic, and testing.							Benjamin Kus Benjamin Ramos Brandon Durham

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SystemUI extends JFrame implements ActionListener
{
	private JLabel addBookLabel;
	private JLabel removeBookLabel;
	private JLabel searchBookLabel;
	private JLabel issueBookLabel;
	private JButton addBookButton;
	private JButton removeBookButton;
	private JButton searchBookButton;
	private JButton issueBookButton;
	
	public SystemUI()
	{
		// Admin Menu JFrame creation and UI configuration.
		setTitle("Admin Menu");
		setBounds(1050, 500, 400, 400);
		setResizable(false);
		setLayout(null);
		
		// Custom Window Closing operation.
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				JOptionPane.showMessageDialog(null,"You have been logged out of the library management system.");
				System.exit(0);
			}
		});
		
		
		// Add Book JButton creation and UI configuration.
		addBookButton = new JButton("Add Book");
		addBookButton.setBounds(95, 50, 200, 50);
		add(addBookButton);
		
		
		// Remove Book JButton creation and UI configuration.
		removeBookButton = new JButton("Remove Book");
		removeBookButton.setBounds(95, 120, 200, 50);
		add(removeBookButton);
		
		
		// Search Book JButton creation and UI configuration.
		searchBookButton = new JButton("Search Book");
		searchBookButton.setBounds(95, 190, 200, 50);
		add(searchBookButton);
		
		
		// Issue Book JButton creation and UI configuration.
		issueBookButton = new JButton("Issue Book");
		issueBookButton.setBounds(95, 260, 200, 50);
		add(issueBookButton);
		
		
        // Add Book Button ActionListener interaction.
		addBookButton.addActionListener(this);
		
		
        // Remove Book Button ActionListener interaction.
		removeBookButton.addActionListener(this);
		
		
        // Search Book Button ActionListener interaction.
		searchBookButton.addActionListener(this);
		
		
        // Issue Book Button ActionListener interaction.
		issueBookButton.addActionListener(this);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Connection connection = connect();
		
		Frame inputBookFrame = new JFrame();
		
		
		if (e.getSource() == addBookButton)
		{
			try
			{
				inputBookFrame.setTitle("Add Book");
				inputBookFrame.setBounds(1100, 570, 300, 300);
				inputBookFrame.setResizable(false);
				inputBookFrame.setLayout(null);
				
				
				JLabel isbnLabel = new JLabel("ISBN:");
				isbnLabel.setBounds(20, 35, 100, 30);
				inputBookFrame.add(isbnLabel);
				
				JTextField isbnField = new JTextField();
				isbnField.setBounds(70, 35, 175, 30);
				inputBookFrame.add(isbnField);
				
				
				JLabel titleLabel = new JLabel("Title:");
				titleLabel.setBounds(20, 75, 100, 30);
				inputBookFrame.add(titleLabel);
				
				JTextField titleField = new JTextField();
				titleField.setBounds(70, 75, 175, 30);
				inputBookFrame.add(titleField);
				
				
				JLabel authorLabel = new JLabel("Author:");
				authorLabel.setBounds(20, 115, 100, 30);
				inputBookFrame.add(authorLabel);
				
				JTextField authorField = new JTextField();
				authorField.setBounds(70, 115, 175, 30);
				inputBookFrame.add(authorField);
				
				
		        JButton submitButton = new JButton("Submit");
		        submitButton.setBounds(70, 180, 175, 30);
		        inputBookFrame.add(submitButton);
				
				
		        submitButton.addActionListener(new ActionListener()
		        {
		        	@Override
		        	public void actionPerformed(ActionEvent e)
		        	{
		        		String isbnInput = isbnField.getText();
		        		String titleInput = titleField.getText();
		        		String authorInput = authorField.getText();
		        		
		        		if (e.getSource() == submitButton && !isbnInput.equals("") && !titleInput.equals("") && !authorInput.equals(""))
		        		{
		        			if (isbnInput.chars().allMatch(Character::isDigit))
		        			{        				
			        			AddBook addBookOperation = new AddBook(connection, isbnInput, titleInput, authorInput);
		        			}
		        			else
		        			{
		        				JOptionPane.showMessageDialog(null,"The ISBN must be numeric and all digits.");
		        			}
		        		}
		        		else
		        		{
		        			JOptionPane.showMessageDialog(null,"To add a book, you must include an ISBN, title, and author.");
		        		}		
		        	}
		        });
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Failure to add book to the database.");
			}
			
			inputBookFrame.setVisible(true);
		}
		
		
		if (e.getSource() == removeBookButton)
		{
			try
			{
				inputBookFrame.setTitle("Remove Book");
				inputBookFrame.setBounds(1100, 570, 300, 300);
				inputBookFrame.setResizable(false);
				inputBookFrame.setLayout(null);
				
				
				JLabel isbnLabel = new JLabel("ISBN:");
				isbnLabel.setBounds(20, 35, 100, 30);
				inputBookFrame.add(isbnLabel);
				
				JTextField isbnField = new JTextField();
				isbnField.setBounds(70, 35, 175, 30);
				inputBookFrame.add(isbnField);
				
				
				JLabel titleLabel = new JLabel("Title:");
				titleLabel.setBounds(20, 75, 100, 30);
				inputBookFrame.add(titleLabel);
				
				JLabel titlePopulatedLabel = new JLabel("");
				titlePopulatedLabel.setBounds(70, 75, 175, 30);
				inputBookFrame.add(titlePopulatedLabel);
				
				
				JLabel authorLabel = new JLabel("Author:");
				authorLabel.setBounds(20, 115, 100, 30);
				inputBookFrame.add(authorLabel);
				
				JLabel authorPopulatedLabel = new JLabel("");
				authorPopulatedLabel.setBounds(70, 115, 175, 30);
				inputBookFrame.add(authorPopulatedLabel);
				
				
		        JButton submitButton = new JButton("Submit");
		        submitButton.setBounds(70, 180, 175, 30);
		        inputBookFrame.add(submitButton);
		        
		        
		        submitButton.addActionListener(new ActionListener()
		        {
		        	@Override
		        	public void actionPerformed(ActionEvent e)
		        	{
		        		String isbnInput = isbnField.getText();
		        		
		        		if (e.getSource() == submitButton && !isbnInput.equals(""))
		        		{
		        			if (isbnInput.chars().allMatch(Character::isDigit))
		        			{        				
		        				RemoveBook removeBookOperation = new RemoveBook(connection, isbnInput);
		        				
		        				String updatedTitle = removeBookOperation.populatedTitle;
		        				String updatedAuthor = removeBookOperation.populatedAuthor;
		        				
		        				titlePopulatedLabel.setText(updatedTitle);
		        				authorPopulatedLabel.setText(updatedAuthor);
		        				
		        				int confirmationInput = 1;
		        				
		        				if (removeBookOperation.doesBookExist == true)
		        				{
		        					confirmationInput = JOptionPane.showConfirmDialog(submitButton, "Are you sure you want to remove this book?");
		        				}
		        				
			        			if (confirmationInput == 0)
			        			{
			        				removeBookOperation.RemoveBookConfirmation(connection, isbnInput);
			        			}
		        			}
		        			else
		        			{
		        				JOptionPane.showMessageDialog(null,"The ISBN must be numeric and all digits.");
		        			}
		        		}
		        		else
		        		{
		        			JOptionPane.showMessageDialog(null,"To remove a book, you must include an ISBN.");
		        		}		
		        	}
		        });
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Failure to remove book from the database.");
			}
			
			inputBookFrame.setVisible(true);
		}
		
		if (e.getSource() == searchBookButton)
		{
			try
			{
				inputBookFrame.setTitle("Search Book");
				inputBookFrame.setBounds(1100, 570, 300, 300);
				inputBookFrame.setResizable(false);
				inputBookFrame.setLayout(null);
				
				
				JLabel isbnLabel = new JLabel("ISBN:");
				isbnLabel.setBounds(20, 35, 100, 30);
				inputBookFrame.add(isbnLabel);
				
				JTextField isbnField = new JTextField();
				isbnField.setBounds(70, 35, 175, 30);
				inputBookFrame.add(isbnField);
				
				
				JLabel titleLabel = new JLabel("Title:");
				titleLabel.setBounds(20, 75, 100, 30);
				inputBookFrame.add(titleLabel);
				
				JLabel titlePopulatedLabel = new JLabel("");
				titlePopulatedLabel.setBounds(70, 75, 175, 30);
				inputBookFrame.add(titlePopulatedLabel);
				
				
				JLabel authorLabel = new JLabel("Author:");
				authorLabel.setBounds(20, 115, 100, 30);
				inputBookFrame.add(authorLabel);
				
				JLabel authorPopulatedLabel = new JLabel("");
				authorPopulatedLabel.setBounds(70, 115, 175, 30);
				inputBookFrame.add(authorPopulatedLabel);
				
				
		        JButton searchButton = new JButton("Search");
		        searchButton.setBounds(70, 180, 175, 30);
		        inputBookFrame.add(searchButton);
		        
		        
		        searchButton.addActionListener(new ActionListener()
		        {
		        	@Override
		        	public void actionPerformed(ActionEvent e)
		        	{
		        		String isbnInput = isbnField.getText();
		        		
		        		if (e.getSource() == searchButton && !isbnInput.equals(""))
		        		{
		        			if (isbnInput.chars().allMatch(Character::isDigit))
		        			{
		        				SearchBook searchBookOperation = new SearchBook(connection, isbnInput);
		        				
		        				String updatedTitle = searchBookOperation.populatedTitle;
		        				String updatedAuthor = searchBookOperation.populatedAuthor;
		        				
		        				titlePopulatedLabel.setText(updatedTitle);
		        				authorPopulatedLabel.setText(updatedAuthor);
		        			}
		        			else
		        			{
		        				JOptionPane.showMessageDialog(null,"The ISBN must be numeric and all digits.");
		        			}
		        		}
		        		else
		        		{
		        			JOptionPane.showMessageDialog(null,"To search for a book, you must include an ISBN.");
		        		}		
		        	}
		        });
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Failure to search book from the database.");
			}
			
			inputBookFrame.setVisible(true);
		}
		
		if (e.getSource() == issueBookButton)
		{
			try
			{
				inputBookFrame.setTitle("Issue Book");
				inputBookFrame.setBounds(1100, 500, 300, 400);
				inputBookFrame.setResizable(false);
				inputBookFrame.setLayout(null);
				
				
				JLabel isbnLabel = new JLabel("ISBN:");
				isbnLabel.setBounds(20, 10, 100, 30);
				inputBookFrame.add(isbnLabel);
				
				JTextField isbnField = new JTextField();
				isbnField.setBounds(70, 10, 175, 30);
				inputBookFrame.add(isbnField);
				
				
				JLabel titleLabel = new JLabel("Title:");
				titleLabel.setBounds(20, 50, 100, 30);
				inputBookFrame.add(titleLabel);
				
				JLabel titlePopulatedLabel = new JLabel("");
				titlePopulatedLabel.setBounds(70, 50, 175, 30);
				inputBookFrame.add(titlePopulatedLabel);
				
				
				JLabel authorLabel = new JLabel("Author:");
				authorLabel.setBounds(20, 90, 100, 30);
				inputBookFrame.add(authorLabel);
				
				JLabel authorPopulatedLabel = new JLabel("");
				authorPopulatedLabel.setBounds(70, 90, 175, 30);
				inputBookFrame.add(authorPopulatedLabel);
				
				
				JLabel nameLabel = new JLabel("Name:");
				nameLabel.setBounds(20, 130, 100, 30);
				inputBookFrame.add(nameLabel);
				
				JTextField nameField = new JTextField();
				nameField.setBounds(70, 130, 175, 30);
				inputBookFrame.add(nameField);
				
				
				JLabel addressLabel = new JLabel("Address:");
				addressLabel.setBounds(20, 170, 100, 30);
				inputBookFrame.add(addressLabel);
				
				JTextField addressField = new JTextField();
				addressField.setBounds(70, 170, 175, 30);
				inputBookFrame.add(addressField);
				
				
				JLabel emailLabel = new JLabel("Email:");
				emailLabel.setBounds(20, 210, 100, 30);
				inputBookFrame.add(emailLabel);
				
				JTextField emailField = new JTextField();
				emailField.setBounds(70, 210, 175, 30);
				inputBookFrame.add(emailField);
				
				
		        JButton issueButton = new JButton("Issue");
		        issueButton.setBounds(70, 290, 175, 30);
		        inputBookFrame.add(issueButton);
				
				
		        issueButton.addActionListener(new ActionListener()
		        {
		        	@Override
		        	public void actionPerformed(ActionEvent e)
		        	{
		        		String isbnInput = isbnField.getText();
		        		String nameInput = nameField.getText();
		        		String addressInput = addressField.getText();
		        		String emailInput = emailField.getText();
		        		
		        		if (e.getSource() == issueButton && !isbnInput.equals("") && !nameInput.equals("") && !addressInput.equals("") && !emailInput.equals(""))
		        		{
		        			if (isbnInput.chars().allMatch(Character::isDigit) && nameInput.chars().allMatch(Character::isAlphabetic))
		        			{
		        				IssueBook issueBookOperation = new IssueBook(connection, isbnInput);
		        				
		        				String updatedTitle = issueBookOperation.populatedTitle;
		        				String updatedAuthor = issueBookOperation.populatedAuthor;
		        				
		        				titlePopulatedLabel.setText(updatedTitle);
		        				authorPopulatedLabel.setText(updatedAuthor);
		        				
		        				int confirmationInput = 1;
		        				
		        				if (issueBookOperation.doesBookExist == true)
		        				{
		        					confirmationInput = JOptionPane.showConfirmDialog(issueButton, "Are you sure you want to issue this book?");
		        				}
		        				
			        			if (confirmationInput == 0)
			        			{
			        				issueBookOperation.IssueBookConfirmation(connection, isbnInput, updatedTitle, updatedAuthor, nameInput, addressInput, emailInput);
			        			}
		        			}
		        			else
		        			{
		        				JOptionPane.showMessageDialog(null,"The ISBN must be numeric digits and the name must be alphabetical characters.");
		        			}
		        		}
		        		else
		        		{
		        			JOptionPane.showMessageDialog(null,"To issue a book, you must include an ISBN, name, address, and email.");
		        		}		
		        	}
		        });
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Failure to issue book from the database.");
			}
			
			inputBookFrame.setVisible(true);
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
