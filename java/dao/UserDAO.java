package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.MYConnection;
import model.User;

public class UserDAO {
	public static boolean isExists(String email) {
		Connection connection = MYConnection.getConnection();
		try {
			PreparedStatement prepared = connection.prepareStatement("Select email from user");
			ResultSet rs=prepared.executeQuery();
			while(rs.next()) {
				String e = rs.getString(1);
				if(e.equals(email)) 
					return true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false; 
	}
		public static int saveUser(User user)throws SQLException{
			Connection connection = MYConnection.getConnection();
			PreparedStatement pre = connection.prepareStatement("insert into user(name,email)values(?,?)");
			pre.setString(1, user.getName());
			pre.setString(2, user.getEmail());
			return pre.executeUpdate();
	}
}
