package mvc.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import mvc.model.User;
import mvc.util.*;

public class LoginService {
	
	public String addUser(User user) {
		
		String username = user.getUsername();
		String password = user.getPassword();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DBConnect.getDatabaseConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO User VALUES (?, ?)");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			int i = preparedStatement.executeUpdate();
			
			preparedStatement.close();
			connection.close();
			/*if exception occur before this line, the database connection would not be closed.
			 * finally block is used to avoid that
			 */
			
			if (i != 0) {
				return "Registration Successful";
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		} finally {
			
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				
				if (connection != null) {
					connection.close();
				}
				
			} catch (SQLException e) {
				
				System.out.println(e.getMessage());
				e.printStackTrace();
				
			}
		}
		
		return "Oops.. Something went wrong there..!";
	}
	
	public ArrayList<User> getUsers() {
		
		ArrayList<User> users = new ArrayList<User>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DBConnect.getDatabaseConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT username FROM User");
			ResultSet resultset = preparedStatement.executeQuery();
			
			while (resultset.next()) {
				User user = new User();
				user.setUsername(resultset.getString(1));
				users.add(user);	
			}
			
			preparedStatement.close();
			connection.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				
				if (connection != null) {
					connection.close();
				}
				
			} catch (SQLException e) {
				
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
		return users;
	}
}
