package ncsu.csc.db.managers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import ncsu.csc.db.beans.Question;
import ncsu.csc.db.models.DBConnector;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

public class HWManger {

	Connection con;
	
	public HWManger() {
		con = DBConnector.dbConnect();
	}
	
	public ArrayList<Question> generateNewAttempt(String hwId, String username)
	{
		ArrayList<Question> arr_questions = new ArrayList<Question>();
		Question ques;	
		
		//************** Sql query to fetch courses ******************
		
		String preparedStatement = "{ CALL GENERATENEWATTEMPT(?,?,?) }";
		CallableStatement cs;
		try {
			cs = con.prepareCall(preparedStatement);
			cs.setString(1, username);
			cs.setString(2, hwId);
			cs.registerOutParameter(3,OracleTypes.CURSOR);
			cs.execute();
			
			ResultSet rs = ((OracleCallableStatement)cs).getCursor(3);
			int seq = 1;
			// ***********************************************************
			while (rs.next()) {
				ques = new Question();
				ques.setQid(rs.getInt("qid"));
				ques.setAttemptId(rs.getInt("attemptId"));
				ques.setText(rs.getString("qtext"));
				ques.setSeqId(seq++);
				ques.setOption1(rs.getString("option1"));
				ques.setOption2(rs.getString("option2"));
				ques.setOption3(rs.getString("option3"));
				ques.setOption4(rs.getString("option4"));
				
				arr_questions.add(ques);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr_questions;
	}
	
	public int saveNewAttempt(int attemptId, int[] questions, int[] answers) {
		try {
			String preparedStatement = "{ CALL submitAnswer(?,?,?,?) }";
			CallableStatement cs = con.prepareCall(preparedStatement);
			
			ArrayDescriptor desc = ArrayDescriptor.createDescriptor("ID_ARRAY", con);
			ARRAY questionsArray = new ARRAY(desc, con, questions);
			ARRAY answersArray = new ARRAY(desc, con, answers);
			cs.setInt(1, attemptId);
			cs.setArray(2, questionsArray);
			cs.setArray(3, answersArray);
			cs.registerOutParameter(4,Types.INTEGER);
			cs.executeUpdate();
			
			int status = ((OracleCallableStatement)cs).getInt(4);
			return status;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
}
