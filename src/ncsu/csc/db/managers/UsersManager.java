package ncsu.csc.db.managers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.sun.nio.sctp.Notification;

import ncsu.csc.db.beans.Enrollments;
import ncsu.csc.db.beans.Notifications;
import ncsu.csc.db.beans.Question;
import ncsu.csc.db.beans.Users;
import ncsu.csc.db.models.DBConnector;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;


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
	
	public String enrollUser(Enrollments enrollments) {
		try {
			String preparedStatement = "{ CALL ENROLL_STUDENT(?,?,?,?) }";
			CallableStatement cs = con.prepareCall(preparedStatement);
			cs.setString(1, enrollments.getUsername());
			cs.setString(2, enrollments.getToken());
			cs.setInt(3, enrollments.getIsta());
			cs.registerOutParameter(4,Types.VARCHAR);
			cs.executeUpdate();
			
			String status = ((OracleCallableStatement)cs).getString(4);
			return status;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "error";
	}

	public int recordLogin(String username) {
		try {
			String preparedStatement = "{ CALL insert_login(?) }";
			CallableStatement cs = con.prepareCall(preparedStatement);
			cs.setString(1, username);
			return cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<Notifications> GetNotifications(String username) {
		ArrayList<Notifications> arr_notifications = new ArrayList<Notifications>();
		Notifications notif;	
		
		//************** Sql query to fetch courses ******************
		
		String preparedStatement = "{ CALL get_notifications(?,?) }";
		CallableStatement cs;
		try {
			cs = con.prepareCall(preparedStatement);
			cs.setString(1, username);
			cs.registerOutParameter(2,OracleTypes.CURSOR);
			cs.execute();
			
			ResultSet rs = ((OracleCallableStatement)cs).getCursor(2);
			// ***********************************************************
			while (rs.next()) {
				notif = new Notifications();
				notif.setId(rs.getInt("noteid"));
				notif.setText(rs.getString("text"));
				notif.setNotificationDate(rs.getString("nodate"));
				arr_notifications.add(notif);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr_notifications;
	}

	public int deleteNotifications(String username) {
		try {
			String preparedStatement = "{ CALL delete_notifications(?) }";
			CallableStatement cs = con.prepareCall(preparedStatement);
			cs.setString(1, username);
			return cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
