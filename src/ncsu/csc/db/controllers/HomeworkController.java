package ncsu.csc.db.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
				// retrieve attempt
				
			} else if(hwId != null) {
				// new attempt
				
				request.setAttribute("hwId", hwId);
				request.setAttribute("token", token);
				request.setAttribute("quesNewList", hwm.generateNewAttempt(hwId, username));
				RequestDispatcher rd = request.getRequestDispatcher("HomeworkNew.jsp");
				rd.forward(request, response);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int count = Integer.parseInt(request.getParameter("count"));
		int attemptId = Integer.parseInt(request.getParameter("attemptId"));
		String token = request.getParameter("token");
		int[] questions = new int[count];
		int[] answers = new int[count];;
		for(int i = 0 ; i< count; i++) {
			int k = i+1;
			questions[i] = Integer.parseInt(request.getParameter(("ques")+k));
			answers[i] = Integer.parseInt(request.getParameter(("ans")+k));
		}
		
		HWManger hwm = new HWManger();
		int result = hwm.saveNewAttempt(attemptId, questions, answers);
		if(result > 0) {
			request.setAttribute("token", token);
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

}
