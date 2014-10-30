package ncsu.csc.db.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ncsu.csc.db.beans.HWRecords;
import ncsu.csc.db.beans.Question;
import ncsu.csc.db.managers.CourseManager;
import ncsu.csc.db.managers.HWManger;

/**
 * Servlet implementation class HomeworkController
 */
public class HomeworkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeworkController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String attemptId = request.getParameter("attemptId");
			String hwId = request.getParameter("hwid");
			String token = request.getParameter("token");
			HWManger hwm = new HWManger();
			HttpSession session = request.getSession(false);
			String username = session.getAttribute("Session_UserName").toString();
			
			if(attemptId != null) {
				// retrieve attempts
				
				request.setAttribute("token", token);		
				request.setAttribute("quesCompletedList", hwm.getAttemptedHomewoks(attemptId, username));
				RequestDispatcher rd = request.getRequestDispatcher("HomeworkCompleted.jsp");
				rd.forward(request, response);
				
				
			} else if(hwId != null) {
				// new attempt
				
				request.setAttribute("hwId", hwId);
				request.setAttribute("token", token);
				ArrayList<Question> questionList = hwm.generateNewAttempt(hwId, username);
				if(questionList != null) {
					request.setAttribute("quesNewList", questionList);
					request.setAttribute("isTA", "NOTA");
					RequestDispatcher rd = request.getRequestDispatcher("HomeworkNew.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("errormsg", "There are no questions for this homework. Enjoy!");
					request.setAttribute("text", "Try again");
					RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
					rd.forward(request, response);
					return;
				}
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		int userrole = Integer.parseInt(session.getAttribute("Session_UserRole").toString());
		String token = request.getParameter("cors_token");
		String username = session.getAttribute("Session_UserName").toString();
		
		if(userrole==0)
		{
			int count = Integer.parseInt(request.getParameter("count"));
			int attemptId = Integer.parseInt(request.getParameter("attemptId"));
			String token_student = request.getParameter("token");
			int[] questions = new int[count];
			int[] answers = new int[count];;
			for(int i = 0 ; i< count; i++) {
				int k = i+1;
				questions[i] = Integer.parseInt(request.getParameter(("ques")+k));
				String ans = request.getParameter(("ans")+k);
				if(ans == null || ans.length() < 1) {
					answers[i] = -1;
				} else {
					answers[i] = Integer.parseInt(ans);
				}
			}
			
			HWManger hwm = new HWManger();
			int result = hwm.saveNewAttempt(attemptId, questions, answers);
			if(result > 0) {
				request.setAttribute("token", token_student);
				request.setAttribute("Referer", request.getRequestURI());
				RequestDispatcher rd = request.getRequestDispatcher("CourseController");
				rd.forward(request, response);
				return;
			} else {
				request.setAttribute("errormsg", "Unable to save your answers at this time!");
				request.setAttribute("text", "Try again");
				RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
				rd.forward(request, response);
				return;
			}
			
		}
		else if(userrole==1)
		{ 
			//Code to add new homework ()
			
			
			
			HWRecords hwr=new HWRecords();
			HWManger hw=new HWManger();
			String hwaction=request.getParameter("hwaction");
			
			if(hwaction.equalsIgnoreCase("addHw")||hwaction.equalsIgnoreCase("updateHw"))
			{
				hwr.setHwName(request.getParameter("hwnameHidden"));
				if(hwr.getHwName()==null)
				{
					hwr.setHwName(request.getParameter("hwname"));
				}
					
				try {
					
					hwr.setStartdate(new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(request.getParameter("startdate")));
					hwr.setEnddate(new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(request.getParameter("enddate")));
				
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				hwr.setNumattempts(Integer.parseInt(request.getParameter("numattempts")));
				hwr.setTopics(request.getParameter("topics"));
				hwr.setMindiffrange(Integer.parseInt(request.getParameter("mindiffrange")));
				hwr.setMaxdiffrange(Integer.parseInt(request.getParameter("maxdiffrange")));
				hwr.setScorescheme(Integer.parseInt(request.getParameter("scorescheme")));
				hwr.setNumquestions(Integer.parseInt(request.getParameter("numquestions")));
				hwr.setCorrectpoints(Integer.parseInt(request.getParameter("correctpoints")));
				hwr.setIncorrectpoints(Integer.parseInt(request.getParameter("incorrectpoints")));
				hwr.setRandomseed(Integer.parseInt(request.getParameter("randomseed")));
				
				int status=0;
				
				if(hwaction.equalsIgnoreCase("addHw"))
				{
					status=hw.addNewHomeworkRecord(token, username,hwr);
				}
				else if(hwaction.equalsIgnoreCase("updateHw"))
				{
					status=hw.updateHomeworkRecord(token, username,hwr);
				}
				
				if(status==-1)
				{
					request.setAttribute("errormsg", "Unable to add new Homework!");
					request.setAttribute("text", "Go Back");
					RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
					rd.forward(request, response);
				}
				else
				{
					CourseManager cm = null;
					try {
						cm = new CourseManager();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						request.setAttribute("hwAssignedList", cm.getAssignedHomeworkRecords(token, username));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					request.setAttribute("coursetoken", token);
					RequestDispatcher rd = request.getRequestDispatcher("CourseProfessorPage.jsp");
					rd.forward(request, response);
				}
			}
			else if(hwaction.equalsIgnoreCase("searchTopic"))
			{
				request.setAttribute("hwtoken",request.getParameter("hwtoken"));
				try {
					request.setAttribute("searchResults", hw.getSearchQuestionsList(request.getParameter("searchtopic"),request.getParameter("hwtoken")));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("qts_action","add");
				request.setAttribute("hwtoken", request.getParameter("hwtoken"));
				request.setAttribute("searchtopic",request.getParameter("searchtopic"));
				request.setAttribute("coursetoken",request.getParameter("coursetoken"));
				
				RequestDispatcher rd = request.getRequestDispatcher("questionsView.jsp");
				rd.forward(request, response);
			}
			
			else if(hwaction.equalsIgnoreCase("add"))
			{
				
					int status=hw.insertQuestionsToHw(Integer.parseInt(request.getParameter("qid")),request.getParameter("hwtoken"));
					
					if(status==-1)
					{
						request.setAttribute("errormsg", "Unable to add this question to Homework!");
						request.setAttribute("text", "Go Back");
						RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
						rd.forward(request, response);
					}
					else{
						
						try {
						request.setAttribute("searchResults", hw.getSearchQuestionsList(request.getParameter("searchtopic"),request.getParameter("hwtoken")));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
				request.setAttribute("qts_action","add");
				request.setAttribute("searchtopic",request.getParameter("searchtopic"));
				request.setAttribute("hwtoken",request.getParameter("hwtoken"));
				request.setAttribute("coursetoken",request.getParameter("coursetoken"));
				RequestDispatcher rd = request.getRequestDispatcher("questionsView.jsp");
				rd.forward(request, response);
				}
					
			}
			else if(hwaction.equalsIgnoreCase("delete"))
			{
				int status=hw.deleteQuestionsFromHw(Integer.parseInt(request.getParameter("qid")),request.getParameter("hwtoken"));
				
				if(status==-1)
				{
					request.setAttribute("errormsg", "Unable to add this question to Homework!");
					request.setAttribute("text", "Go Back");
					RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
					rd.forward(request, response);
				}
				else{
					
					try {
						request.setAttribute("searchResults", hw.getQuestionsList(request.getParameter("hwtoken")));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
			request.setAttribute("qts_action","delete");
			request.setAttribute("searchtopic",request.getParameter("searchtopic"));
			request.setAttribute("hwtoken",request.getParameter("hwtoken"));
			request.setAttribute("coursetoken",request.getParameter("coursetoken"));
			RequestDispatcher rd = request.getRequestDispatcher("questionsView.jsp");
			rd.forward(request, response);
				}
			}
			
			else if(hwaction.equalsIgnoreCase("finish"))
			{
				CourseManager cm = null;
				try {
					cm = new CourseManager();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					request.setAttribute("hwAssignedList", cm.getAssignedHomeworkRecords(request.getParameter("coursetoken"), username));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				request.setAttribute("coursetoken", request.getParameter("coursetoken"));
				RequestDispatcher rd = request.getRequestDispatcher("CourseProfessorPage.jsp");
				rd.forward(request, response);
			}
			
			
			
		}
					
	}

}
