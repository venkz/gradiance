package ncsu.csc.db.managers;

import java.sql.Connection;
import java.sql.SQLException;

import ncsu.csc.db.models.DBConnector;

public class ReportManager {

	Connection con;
	
	public ReportManager() throws ClassNotFoundException, SQLException {
		con = DBConnector.dbConnect();
	}
	
}
