package ncsu.csc.db.models;
/*
 * The descriptive text explaining the purpose and use of the function.
 * @param <type:LoanManagemenetConnector> <Description> To establish the connection and close the connection
 * @return <type: con> <Description : To return a connection object>
 * @exception  <ClassNotFoundException,SQLException>

 * @see <references: All the manager classes>
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	
	private static Connection con;

	public static Connection dbConnect() {
		try{
			if(con == null) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url = "jdbc:oracle:thin:@ora.CSC.ncsu.edu:1521:orcl";
				String user = "vkara";
		        String password = "200023607";
		        String schemaName = "CSC540";
				con = DriverManager.getConnection(url, user,password);
				return con;
			} else {
				return con;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void dbDisconnect(Connection c) {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DBConnector doa = new DBConnector();
			Connection con = doa.dbConnect();
			System.out.println(con.toString());
			doa.dbDisconnect(con);
	}
}
