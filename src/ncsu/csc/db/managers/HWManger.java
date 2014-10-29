package ncsu.csc.db.managers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;
import ncsu.csc.db.beans.HWRecords;
import ncsu.csc.db.beans.Question;
import ncsu.csc.db.models.DBConnector;

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

	public int addNewHomeworkRecord(String token, String username,	HWRecords hwr) {
		// TODO Auto-generated method stub
		try {
			String preparedStatement = "{ CALL INSERT_HWRECORD(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
			CallableStatement cs = con.prepareCall(preparedStatement);
			cs.setString(1, hwr.getHwName());
			cs.setString(2, token);
			cs.setDate(3, new Date(hwr.getStartdate().getTime()));
			cs.setDate(4, new Date(hwr.getEnddate().getTime()));
			cs.setInt(5, hwr.getNumattempts());
			cs.setString(6, hwr.getTopics());
			cs.setInt(7, hwr.getMindiffrange());
			cs.setInt(8, hwr.getMaxdiffrange());
			cs.setInt(9, hwr.getScorescheme());
			cs.setInt(10, hwr.getNumquestions());
			cs.setInt(11, hwr.getCorrectpoints());
			cs.setInt(12, hwr.getIncorrectpoints());
			cs.setInt(13, hwr.getRandomseed());
			
			cs.registerOutParameter(14,Types.INTEGER);
			cs.executeUpdate();
			
			int status = ((OracleCallableStatement)cs).getInt(14);
			return status;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int updateHomeworkRecord(String token, String username, HWRecords hwr) {
		try {
			String preparedStatement = "{ CALL update_hwrecord(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
			CallableStatement cs = con.prepareCall(preparedStatement);
			cs.setString(1, hwr.getHwName());
			cs.setString(2, token);
			cs.setDate(3, new Date(hwr.getStartdate().getTime()));
			cs.setDate(4, new Date(hwr.getEnddate().getTime()));
			cs.setInt(5, hwr.getNumattempts());
			cs.setString(6, hwr.getTopics());
			cs.setInt(7, hwr.getMindiffrange());
			cs.setInt(8, hwr.getMaxdiffrange());
			cs.setInt(9, hwr.getScorescheme());
			cs.setInt(10, hwr.getNumquestions());
			cs.setInt(11, hwr.getCorrectpoints());
			cs.setInt(12, hwr.getIncorrectpoints());
			cs.setInt(13, hwr.getRandomseed());
			
			cs.registerOutParameter(14,Types.INTEGER);
			cs.executeUpdate();
			
			int status = ((OracleCallableStatement)cs).getInt(14);
			return status;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
}
