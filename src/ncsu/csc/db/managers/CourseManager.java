package ncsu.csc.db.managers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ncsu.csc.db.beans.Enrollments;
import ncsu.csc.db.beans.HWRecords;
import ncsu.csc.db.beans.ModelJTable;
import ncsu.csc.db.models.DBConnector;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;

public class CourseManager {

Connection con;
	
	public CourseManager() throws ClassNotFoundException, SQLException {
		con = DBConnector.dbConnect();
	}
	
	public ArrayList<Enrollments> GetCourseList(String username, int role) throws SQLException
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
		if(role == 0) {
			while(rs.next()){
				enr=new Enrollments();
				if(rs.getInt("ta")==1)
					enr.setIstaStr("TA");
				else
					enr.setIstaStr("Student");
				enr.setToken(rs.getString("token"));
				enr.setCoursename(rs.getString("coursename"));
				arr_enroll.add(enr);
			}
		} else {
			while(rs.next()){
				enr=new Enrollments();
				enr.setToken(rs.getString("token"));
				enr.setCoursename(rs.getString("coursename"));
				arr_enroll.add(enr);
			}
		}
		return arr_enroll;
	}
	
	public ArrayList<HWRecords> getHomeworkRecords(String token, String username) throws SQLException
	{
		ArrayList<HWRecords> arr_hw = new ArrayList<HWRecords>();
		HWRecords hw;	
		
		//************** Sql query to fetch courses ******************
		
		String preparedStatement = "{ CALL getPastSubmissionInfo(?,?,?) }";
		CallableStatement cs = con.prepareCall(preparedStatement);
		cs.setString(1, username);
		cs.registerOutParameter(2,OracleTypes.CURSOR);
		cs.setString(3, token);
		cs.execute();
		
		ResultSet rs = ((OracleCallableStatement)cs).getCursor(2);
		
		// ***********************************************************
		while (rs.next()) {
			hw = new HWRecords();
			hw.setHwId(rs.getInt("hwid"));
			hw.setAttemptId(rs.getInt("attemptid"));
			hw.setAttemptNumber(rs.getInt("attemptno"));
			hw.setScore(rs.getInt("score"));
			hw.setHwName(rs.getString("hwname"));
			arr_hw.add(hw);
		}
		return arr_hw;
	}
	
	public ArrayList<HWRecords> getNewHomeworks(String token, String username) throws SQLException
	{
		ArrayList<HWRecords> arr_hw = new ArrayList<HWRecords>();
		HWRecords hw;	
		
		//************** Sql query to fetch courses ******************
		
		String preparedStatement = "{ CALL getValidAttemptHomework(?,?,?) }";
		CallableStatement cs = con.prepareCall(preparedStatement);
		cs.setString(1, username);
		cs.registerOutParameter(2,OracleTypes.CURSOR);
		cs.setString(3, token);
		cs.execute();
		
		ResultSet rs = ((OracleCallableStatement)cs).getCursor(2);
		
		// ***********************************************************
		while (rs.next()) {
			hw = new HWRecords();
			hw.setHwId(rs.getInt("hwid"));
			hw.setHwName(rs.getString("hwname"));
			hw.setMaxAttempts(rs.getInt("maxattempts"));
			hw.setAttempsMade(getCountOfAttempts(username, hw.getHwId()));
			arr_hw.add(hw);
		}
		return arr_hw;
	}

	private int getCountOfAttempts(String username, int hwId) {
		String preparedStatement = "{ CALL getNoOfAttemptHomework(?,?,?) }";
		CallableStatement cs;
		try {
			cs = con.prepareCall(preparedStatement);
			cs.setString(1, username);
			cs.registerOutParameter(2,Types.INTEGER);
			cs.setInt(3, hwId);
			cs.executeUpdate();
			int status = ((OracleCallableStatement)cs).getInt(2);
			return status;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<HWRecords> getAssignedHomeworkRecords(String token, String username) throws SQLException
	{
		ArrayList<HWRecords> arr_hw = new ArrayList<HWRecords>();
		HWRecords hw;	
		
		//************** Sql query to fetch assigned Homeworks in a particular course ******************
		
		String preparedStatement = "{ CALL viewhomeworks(?,?,?) }";
		CallableStatement cs = con.prepareCall(preparedStatement);
		cs.setString(1, username);
		cs.registerOutParameter(2,OracleTypes.CURSOR);
		cs.setString(3, token);
		cs.execute();
		
		ResultSet rs = ((OracleCallableStatement)cs).getCursor(2);
		
		// ***********************************************************
		while (rs.next()) {
			hw = new HWRecords();
			hw.setHwId(rs.getInt("hwid"));
			hw.setHwName(rs.getString("hwname"));
			arr_hw.add(hw);
		}
		return arr_hw;
		
	}

	public HWRecords getHomeworkParameters(String hwtoken) throws SQLException {
		// TODO Auto-generated method stub
		HWRecords hwr = null;	
		String preparedStatement = "{ CALL get_hw_details(?,?) }";
		CallableStatement cs = con.prepareCall(preparedStatement);
		cs.setString(1, hwtoken);
		cs.registerOutParameter(2,OracleTypes.CURSOR);
		cs.execute();
		
		ResultSet rs = ((OracleCallableStatement)cs).getCursor(2);
		
		// ***********************************************************
		if(rs.next()) {
			
			hwr = new HWRecords();
			hwr.setHwName(rs.getString("hwname"));

			try {
				
				hwr.setStartdate(new SimpleDateFormat("MM/dd/yyyy").parse(rs.getString("stdate")));
				hwr.setEnddate(new SimpleDateFormat("MM/dd/yyyy").parse(rs.getString("endate")));
			
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			hwr.setNumattempts(Integer.parseInt(rs.getString("maxattempts")));
			hwr.setTopics(rs.getString("topic"));
			hwr.setMindiffrange(Integer.parseInt(rs.getString("mindiff")));
			hwr.setMaxdiffrange(Integer.parseInt(rs.getString("maxdiff")));
			hwr.setScorescheme(Integer.parseInt(rs.getString("scoremethod")));
			hwr.setNumquestions(Integer.parseInt(rs.getString("noofqsts")));
			hwr.setCorrectpoints(Integer.parseInt(rs.getString("qpoints")));
			hwr.setIncorrectpoints(Integer.parseInt(rs.getString("penaltypoints")));
			hwr.setRandomseed(Integer.parseInt(rs.getString("randseed")));
			
			return hwr;
	
		}
		return null;
		
		
	}
	
}
