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

	public void generateReports(String hwName) {
		
		String preparedStatement = "{ CALL createReportsforHomework(?,?,?) }";
		CallableStatement cs;
		try {
			cs = con.prepareCall(preparedStatement);
			cs.setString(1, hwName);
			cs.registerOutParameter(2,OracleTypes.CURSOR);
			cs.registerOutParameter(3,OracleTypes.CURSOR);
			cs.execute();
			
			ResultSet rs1 = ((OracleCallableStatement)cs).getCursor(2);
			ResultSet rs2 = ((OracleCallableStatement)cs).getCursor(3);
			
			ModelJTable mdl = new ModelJTable(rs1, "Defaulters for this homework");
			ModelJTable mdl2 = new ModelJTable(rs2, "Students scoring max in attemp #1");

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
	
	
	
	
}
