package mvc.util;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBConnect {
	
	private static Connection connection;
	
	public static Connection getDatabaseConnection() throws SQLException, ClassNotFoundException {
		
		if (connection == null || connection.isClosed()) {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/samplemvc", "root", "");
		}
		
		return connection;
	}
}
