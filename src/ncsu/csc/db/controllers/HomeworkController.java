package ncsu.csc.db.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ncsu.csc.db.beans.HWRecords;
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
			HWManger hwm = new HWManger();
			HttpSession session = request.getSession(false);
			String username = session.getAttribute("Session_UserName").toString();
			
			if(attemptId != null) {
				// retrieve attempt
				
			} else if(hwId != null) {
				// new attempt
				
				request.setAttribute("hwId", hwId);
				request.setAttribute("quesNewList", hwm.generateNewAttempt(hwId, username));
				RequestDispatcher rd = request.getRequestDispatcher("HomeworkNew.jsp");
				rd.forward(request, response);
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
			
			
		}
		else if(userrole==1)
		{ 
			//Code to add new homework ()
			HWRecords hwr=new HWRecords();
			HWManger hw=new HWManger();
			int edit_status=Integer.parseInt(request.getParameter("updateHW"));
			
			
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
				
				if(edit_status!=1)
				{
					status=hw.addNewHomeworkRecord(token, username,hwr);
				}
				else
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
			else
			{
				
				
			}
					
	}

}
