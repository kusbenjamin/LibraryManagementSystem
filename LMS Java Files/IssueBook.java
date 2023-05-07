// CMSC 495 7380
// Benjamin Kus
// Benjamin Ramos
// Brandon Durham

// REVISION HISTROY
// ---------------------------------------------------------------------------------------------------------------------------
// 1.0 Initial creation of class.										Benjamin Kus Benjamin Ramos
// 1.1 Finalizing class, logic, and testing.							Benjamin Kus Benjamin Ramos Brandon Durham

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class IssueBook
{
	public String populatedTitle;
	public String populatedAuthor;
	public boolean doesBookExist = true;
	
	public IssueBook(Connection connect, String isbn)
	{
		try
		{
			Statement stmt = connect.createStatement();
			String st = ("SELECT * FROM `library`.`books` WHERE book_isbn = '" + isbn + "'");
			ResultSet rs = stmt.executeQuery(st);
			
			if (rs.next() == false)
			{
				doesBookExist = false;
				JOptionPane.showMessageDialog(null, "This book does not exists in the database.");
			}
			else
			{
				populatedTitle = rs.getString("book_name");
				populatedAuthor = rs.getString("book_author");
			}
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Failure to issue book in the database.");
		}
	}
	
	public void IssueBookConfirmation(Connection connect, String isbn, String title, String author, String name, String address, String email)
	{
		try
		{
			Statement stmt = connect.createStatement();
			String st = ("INSERT INTO `library`.`issued_books`(book_isbn, book_name, book_author, borrower_name, borrower_address, borrower_email) VALUES('" + isbn + "', '" + title + "', '" + author + "', '" + name + "', '" + address + "', '" + email + "')");
			stmt.executeUpdate(st);
			JOptionPane.showMessageDialog(null, "Your book has been issued.");
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Failure to issue book in the database.");
		}
	}
}
