// CMSC 495 7380
// Benjamin Kus
// Benjamin Ramos
// Brandon Durham

// REVISION HISTROY
// ---------------------------------------------------------------------------------------------------------------------------
// 1.0 Initial creation of class.										Benjamin Kus Benjamin Ramos

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
		setBounds(1000, 500, 500, 500);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//add book
		addBookLabel = new JLabel("Add Book:");
		addBookLabel.setBounds(30, 65, 100, 30);
		add(addBookLabel);
		
		addBookButton = new JButton();
		addBookButton.setBounds(130, 65, 200, 30);
		add(addBookButton);
		
		//remove book
		removeBookLabel = new JLabel("Remove Book:");
		removeBookLabel.setBounds(30, 100, 100, 30);
		add(removeBookLabel);
		
		removeBookButton = new JButton();
		removeBookButton.setBounds(130, 100, 200, 30);
		add(removeBookButton);
		
		//search book
		searchBookLabel = new JLabel("Search Book:");
		searchBookLabel.setBounds(30, 135, 100, 30);
		add(searchBookLabel);
		
		searchBookButton = new JButton();
		searchBookButton.setBounds(130, 135, 200, 30);
		add(searchBookButton);
		
		//issue book
		issueBookLabel = new JLabel("Issue Book:");
		issueBookLabel.setBounds(30, 170, 100, 30);
		add(issueBookLabel);
		
		issueBookButton = new JButton();
		issueBookButton.setBounds(130, 170, 200, 30);
		add(issueBookButton);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
	}
}
