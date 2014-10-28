package ncsu.csc.db.managers;

import java.sql.Connection;

import ncsu.csc.db.models.DBConnector;

public class HWManger {

	Connection con;
	
	public HWManger() {
		con = DBConnector.dbConnect();
	}
	
	
}
