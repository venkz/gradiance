package ncsu.csc.db.managers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;
import ncsu.csc.db.beans.ModelJTable;
import ncsu.csc.db.models.DBConnector;

public class ReportManager {

	Connection con;
	
	public ReportManager()  {
		con = DBConnector.dbConnect();
	}

	public void generateReportsHWOnly(String hwName) {
		
		String preparedStatement = "{ CALL createReportsforHomework(?,?,?,?,?,?,?,?) }";
		CallableStatement cs;
		try {
			cs = con.prepareCall(preparedStatement);
			cs.setString(1, hwName);
			cs.registerOutParameter(2,OracleTypes.CURSOR);
			cs.registerOutParameter(3,OracleTypes.CURSOR);
			cs.registerOutParameter(4,OracleTypes.CURSOR);
			cs.registerOutParameter(5,OracleTypes.CURSOR);
			cs.registerOutParameter(6,OracleTypes.CURSOR);
			cs.registerOutParameter(7,OracleTypes.CURSOR);
			cs.registerOutParameter(8,OracleTypes.CURSOR);
			cs.execute();
			
			ResultSet rs1 = ((OracleCallableStatement)cs).getCursor(2);
			ResultSet rs2 = ((OracleCallableStatement)cs).getCursor(3);
			ResultSet rs3 = ((OracleCallableStatement)cs).getCursor(4);
			ResultSet rs4 = ((OracleCallableStatement)cs).getCursor(5);
			ResultSet rs5 = ((OracleCallableStatement)cs).getCursor(6);
			ResultSet rs6 = ((OracleCallableStatement)cs).getCursor(7);
			ResultSet rs7 = ((OracleCallableStatement)cs).getCursor(8);
			
			ModelJTable mdl = new ModelJTable(rs1, "Defaulters for "+hwName);
			ModelJTable mdl2 = new ModelJTable(rs2, "Students scoring max in attemp #1 for "+hwName);
			ModelJTable mdl3 = new ModelJTable(rs3, "Number of attempts for "+hwName);
			ModelJTable mdl4 = new ModelJTable(rs4, "Max and Min for a Question on each attempt in "+hwName);
			ModelJTable mdl5 = new ModelJTable(rs5, "Correct attempts for a question in "+hwName);
			ModelJTable mdl6 = new ModelJTable(rs6, "Incorrect attempts for a quesion in "+hwName);
			ModelJTable mdl7 = new ModelJTable(rs7, "Average of attempts for "+hwName);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
public void generateReportsStudentOnly(String token, String username) {
		
		String preparedStatement = "{ CALL createReportforStudent(?,?,?,?) }";
		CallableStatement cs;
		try {
			cs = con.prepareCall(preparedStatement);
			cs.setString(1, username);
			cs.setString(2, token);
			cs.registerOutParameter(3,OracleTypes.CURSOR);
			cs.registerOutParameter(4,OracleTypes.CURSOR);
			cs.execute();
			
			ResultSet rs2 = ((OracleCallableStatement)cs).getCursor(3);
			ResultSet rs3 = ((OracleCallableStatement)cs).getCursor(4);
			
			ModelJTable mdl2 = new ModelJTable(rs2, "Average score for this homework for"+username);
			ModelJTable mdl3 = new ModelJTable(rs3, "Score/ # of attempts for "+username);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void generateQuestionChoices(int qid) {
		
		String preparedStatement = "{ CALL get_answer_choices(?,?) }";
		CallableStatement cs;
		try {
			cs = con.prepareCall(preparedStatement);
			cs.setInt(1, qid);
			cs.registerOutParameter(2,OracleTypes.CURSOR);
			cs.execute();
			
			ResultSet rs1 = ((OracleCallableStatement)cs).getCursor(2);
			
			ModelJTable mdl = new ModelJTable(rs1, "Choices");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	

public void generateReportsForBoth(String hwName, String username,String token ) {
	
	String preparedStatement = "{ CALL createReportforHWandStudent(?,?,?,?) }";
	CallableStatement cs;
	try {
		cs = con.prepareCall(preparedStatement);
		cs.setString(1, hwName);
		cs.setString(2, username);
		cs.setString(3, token);
		cs.registerOutParameter(4,OracleTypes.CURSOR);
		cs.execute();
		
		ResultSet rs2 = ((OracleCallableStatement)cs).getCursor(4);
		
		ModelJTable mdl2 = new ModelJTable(rs2, "Attempt level details for "+username+" in homework "+hwName);

	} catch (SQLException e) {
		e.printStackTrace();
	}
}

public void generateFullReport(String token) {
	String preparedStatement = "{ CALL createFullReport(?,?,?,?) }";
	CallableStatement cs;
	try {
		cs = con.prepareCall(preparedStatement);
		cs.setString(1, token);
		cs.registerOutParameter(2,OracleTypes.CURSOR);
		cs.registerOutParameter(3,OracleTypes.CURSOR);
		cs.registerOutParameter(4,OracleTypes.CURSOR);
		cs.execute();
		
		ResultSet rs1 = ((OracleCallableStatement)cs).getCursor(2);
		ResultSet rs2 = ((OracleCallableStatement)cs).getCursor(3);
		ResultSet rs3 = ((OracleCallableStatement)cs).getCursor(4);
		
		ModelJTable mdl1 = new ModelJTable(rs1, "Students scoring max in attempt #1");
		ModelJTable mdl2 = new ModelJTable(rs2, "Scoring stats for each homework");
		ModelJTable mdl3 = new ModelJTable(rs3, "Attempt stats for each homework");


	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}
