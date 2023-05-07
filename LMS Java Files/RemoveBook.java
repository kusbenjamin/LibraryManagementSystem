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

public class RemoveBook
{
	public String populatedTitle;
	public String populatedAuthor;
	public boolean doesBookExist = true;
	
	public RemoveBook(Connection connect, String isbn)
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
			JOptionPane.showMessageDialog(null, "Failure to remove book from the database.");
		}
	}
		
	public void RemoveBookConfirmation(Connection connect, String isbn)
	{
		try
		{
			Statement stmt = connect.createStatement();
			String st = ("SET SQL_SAFE_UPDATES = 0;");
			stmt.executeUpdate(st);
			st = ("DELETE FROM `library`.`books` WHERE book_isbn = '" + isbn + "';");
			stmt.executeUpdate(st);
			st = ("SET SQL_SAFE_UPDATES = 1;");
			stmt.executeUpdate(st);
			JOptionPane.showMessageDialog(null, "Your book has been deleted from the database.");
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Failure to remove book from the database.");
		}
	}
}
