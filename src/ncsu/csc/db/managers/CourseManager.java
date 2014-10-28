package ncsu.csc.db.managers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;
import ncsu.csc.db.beans.Enrollments;
import ncsu.csc.db.models.DBConnector;

public class CourseManager {

Connection con;
	
	public CourseManager() throws ClassNotFoundException, SQLException {
		con = DBConnector.dbConnect();
	}
	
	public ArrayList<Enrollments> GetCourseList(String username) throws SQLException
	{
		ArrayList<Enrollments> arr_enroll=new ArrayList<Enrollments>();
		Enrollments enr;	
		
		//************** Sql query to fetch courses ******************
		
		String preparedStatement = "{ CALL list_courses(?,?) }";
		CallableStatement cs = con.prepareCall(preparedStatement);
		cs.setString(1, username);
		cs.registerOutParameter(2,OracleTypes.CURSOR);
		cs.execute();
		
		ResultSet rs = ((OracleCallableStatement)cs).getCursor(2);
		
		// ***********************************************************
		
		while(rs.next()!=false){
			
			enr=new Enrollments();
			
			if(rs.getInt("ta")==1)
				enr.setIstaStr("TA");
			else
				enr.setIstaStr("Student");
			
			enr.setToken(rs.getString("token"));
			enr.setCoursename(rs.getString("coursename"));
			
			arr_enroll.add(enr);
			
		}

		return arr_enroll;
	}
	
	
}
