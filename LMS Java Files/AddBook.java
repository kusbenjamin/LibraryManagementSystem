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

public class AddBook
{
	public AddBook(Connection connect, String isbn, String title, String author)
	{
		try
		{
			Statement stmt = connect.createStatement();
			String st = ("SELECT book_isbn FROM `library`.`books` WHERE book_isbn = '" + isbn + "'");
			ResultSet rs = stmt.executeQuery(st);
			
			if (rs.next() == true)
			{
				JOptionPane.showMessageDialog(null, "This book already exists in the database.");
			}
			else
			{
				st = ("INSERT INTO `library`.`books`(book_isbn, book_name, book_author) VALUES('" + isbn + "', '" + title + "', '" + author + "')");
				stmt.executeUpdate(st);
				JOptionPane.showMessageDialog(null, "Your book has been added to the database.");
			}			
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Failure to add book to the database.");
		}
	}
}
