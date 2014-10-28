package ncsu.csc.db.managers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ncsu.csc.db.beans.Enrollments;
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
	
	public int insertUser(Users users) {
		try {
			String preparedStatement = "{ CALL CREATE_USER(?,?,?,?,?,?,?) }";
			CallableStatement cs = con.prepareCall(preparedStatement);
			cs.setString(1, users.getUserName());
			cs.setString(2, users.getPassword());
			cs.setString(3, users.getFname());
			cs.setString(4, users.getLname());
			cs.setString(5, users.getEmail());
			cs.setInt(6, users.getDegree());
			cs.setInt(7, 0);
			return cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int enrollUser(Enrollments enrollments) {
		try {
			String preparedStatement = "{ CALL ENROLL_STUDENT(?,?,?) }";
			CallableStatement cs = con.prepareCall(preparedStatement);
			cs.setString(1, enrollments.getUsername());
			cs.setString(2, enrollments.getToken());
			cs.setInt(3, enrollments.getIsta());
			return cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
