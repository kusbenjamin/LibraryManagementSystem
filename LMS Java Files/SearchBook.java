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

public class SearchBook
{
	public String populatedTitle;
	public String populatedAuthor;
	
	public SearchBook(Connection connect, String isbn)
	{
		try
		{
			Statement stmt = connect.createStatement();
			String st = ("SELECT * FROM `library`.`books` WHERE book_isbn = '" + isbn + "'");
			ResultSet rs = stmt.executeQuery(st);
			
			if (rs.next() == false)
			{
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
			JOptionPane.showMessageDialog(null, "Failure to search book in the database.");
		}
	}
}
