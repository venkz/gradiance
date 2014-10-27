package ncsu.csc.db.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ncsu.csc.db.beans.Users;
import ncsu.csc.db.models.DBConnector;


public class UsersManager {
	
	Connection con;
	
	public UsersManager() throws ClassNotFoundException, SQLException {
		con = DBConnector.dbConnect();
	}

	public int validateLogin(Users users) throws SQLException {
		
		String selectStatement = "SELECT * FROM Users WHERE username = ? and password = ?";
		PreparedStatement prepStmt = con.prepareStatement(selectStatement);
		prepStmt.setString(1, users.getUserName());
		prepStmt.setString(2, users.getPassword());
		ResultSet rs = prepStmt.executeQuery();
		
		if(rs.next()) {
			return rs.getInt("role");
		}
		return -1;
	}
}
