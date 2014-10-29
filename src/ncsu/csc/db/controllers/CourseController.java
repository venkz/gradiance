package ncsu.csc.db.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ncsu.csc.db.beans.HWRecords;
import ncsu.csc.db.managers.CourseManager;

/**
 * Servlet implementation class CourseController
 */
public class CourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			
			CourseManager cm = new CourseManager();
			HttpSession session = request.getSession(false);
			String username = session.getAttribute("Session_UserName").toString();
			int userrole = Integer.parseInt(session.getAttribute("Session_UserRole").toString());
			
			if(userrole==0)
			{
				String token = request.getParameter("token");
				request.setAttribute("hwCompletedList", cm.getHomeworkRecords(token, username));
				request.setAttribute("hwNewList", cm.getNewHomeworks(token, username));
				RequestDispatcher rd = request.getRequestDispatcher("CourseStudentPage.jsp");
				rd.forward(request, response);
			}
			else if(userrole==1)
			{
				
				String action = request.getParameter("action");
				
				if(action==null){
					
					String coursetoken = request.getParameter("coursetoken");
					request.setAttribute("coursetoken", coursetoken);
					request.setAttribute("hwAssignedList", cm.getAssignedHomeworkRecords(coursetoken, username));
					RequestDispatcher rd = request.getRequestDispatcher("CourseProfessorPage.jsp");
					rd.forward(request, response);
				}
				else if(action.equalsIgnoreCase("Edit")){
					
					String hwtoken = request.getParameter("hwtoken");
					String coursetoken= request.getParameter("coursetoken");
					request.setAttribute("hwtoken", request.getParameter("hwtoken"));
					request.setAttribute("coursetoken", request.getParameter("coursetoken"));
					HWRecords hwr=cm.getHomeworkParameters(hwtoken);
					if(hwr!=null)
					{
						request.setAttribute("coursetoken", coursetoken);
						request.setAttribute("hwAssignedList", cm.getAssignedHomeworkRecords(coursetoken, username));
						request.setAttribute("hwParameters", hwr);
						
						RequestDispatcher rd = request.getRequestDispatcher("CourseProfessorPage.jsp");
						rd.forward(request, response);
					}
					else
					{	
						request.setAttribute("errormsg", "Unable to Retrive Homework!");
						request.setAttribute("text", "Go Back");
						RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
						rd.forward(request, response);
					}
				}
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getAttribute("Referer").toString().contains("Homework")) {
			doGet(request, response);
		}
	}

}
