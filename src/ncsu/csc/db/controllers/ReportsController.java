package ncsu.csc.db.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ncsu.csc.db.managers.ReportManager;

/**
 * Servlet implementation class ReportsController
 */
public class ReportsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String homework = request.getParameter("homework");
		String student = request.getParameter("student");
		String token = request.getParameter("token");
		
		if((homework.length()>1) && (student.length()<1)) {
			
			ReportManager rm = new ReportManager();
			rm.generateReportsHWOnly(homework);
			RequestDispatcher rd = request.getRequestDispatcher("Reports.jsp");
			rd.forward(request, response);
			return;	
			
		} else if ((homework.length()<1) && (student.length()>1)) {
			ReportManager rm = new ReportManager();
			rm.generateReportsStudentOnly(token,student );
			RequestDispatcher rd = request.getRequestDispatcher("Reports.jsp");
			rd.forward(request, response);
			return;	
		} else if ((homework.length()>1) && (student.length()>1)){
			ReportManager rm = new ReportManager();
			rm.generateReportsForBoth(homework, student, token);
			RequestDispatcher rd = request.getRequestDispatcher("Reports.jsp");
			rd.forward(request, response);
			return;	
		} else {
			ReportManager rm = new ReportManager();
			rm.generateFullReport(token);
			RequestDispatcher rd = request.getRequestDispatcher("Reports.jsp");
			rd.forward(request, response);
			return;	
		}
		
		
	}

}
