package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class MYConnection {
	 static String jdbcURL = "jdbc:mysql://localhost:3306/authenticationproject";
     static String dbUser = "root";
     static String dbPassword = "W7301@jqir#";
	private static Connection connection;
	public static Connection getConnection() {
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(jdbcURL,dbUser,dbPassword);
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("Connection successfull");
	return connection;
		
	}
	public static void closeConnection() {
	if(connection !=null) {
		try {
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	}

}
