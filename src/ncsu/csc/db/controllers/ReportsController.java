package ncsu.csc.db.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ncsu.csc.db.beans.HWRecords;
import ncsu.csc.db.managers.CourseManager;
import ncsu.csc.db.managers.HWManger;
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
		ReportManager rm=new ReportManager();
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
		HWRecords hwr;
		try {
			hwr=cm.getHomeworkParameters(request.getParameter("hwtoken"));
		 
		HttpSession session=request.getSession(false);
		HWManger hw = new HWManger();
		
		rm.generateQuestionChoices(Integer.parseInt(request.getParameter("qid")));
		
		
		request.setAttribute("coursetoken", request.getParameter("coursetoken"));
		request.setAttribute("hwAssignedList", cm.getAssignedHomeworkRecords(request.getParameter("coursetoken"), session.getAttribute("Session_UserName").toString()));
		request.setAttribute("hwParameters", hwr);
		request.setAttribute("isTA", "NOTA");
		request.setAttribute("searchResults", hw.getQuestionsList(request.getParameter("hwtoken")));
		request.setAttribute("hwtoken", request.getParameter("hwtoken"));
		request.setAttribute("qts_action","view");
		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("questionsView.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String homework = request.getParameter("homework");
		String student = request.getParameter("student");
		
		if((homework != null) && (student.length()<1)) {
			
			ReportManager rm = new ReportManager();
			rm.generateReports(homework);
			RequestDispatcher rd = request.getRequestDispatcher("Reports.jsp");
			rd.forward(request, response);
			return;	
			
		} else if ((homework.length()<1) && (student != null)) {
			
		} else {
			
		}
		
		
	}

}
