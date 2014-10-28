package ncsu.csc.db.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import ncsu.csc.db.beans.Enrollments;
import ncsu.csc.db.beans.Users;
import ncsu.csc.db.managers.CourseManager;
import ncsu.csc.db.managers.UsersManager;

/**
 * Servlet implementation class Sample
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("Hello World venkz");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			UsersManager userMan = new UsersManager();
			Users users = new Users();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String Rolename;
			users.setUserName(username);
			users.setPassword(password);
			int role = userMan.validateLogin(users);
			
			if(role == -1) {
				request.setAttribute("errormsg", "Invalid login details!");
				request.setAttribute("link", "Login.jsp");
				request.setAttribute("text", "Try again");
				RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
				rd.forward(request, response);
				return;
			} else {
				
				HttpSession session = request.getSession();
				request.setAttribute("Username", username);
				
				if(role==0)
				{
					session.setAttribute("Session_UserName", username);
					session.setAttribute("Session_UserRole", role);
					request.setAttribute("Rolename","Student");
					CourseManager cm=new CourseManager();
					request.setAttribute("CourseList",cm.GetCourseList(session.getAttribute("Session_UserName").toString()));
				}
				else {
					request.setAttribute("Rolename","Professor");
					session.setAttribute("Session_UserRole", role);
				}
					
				RequestDispatcher rd = request.getRequestDispatcher("LandingPage.jsp");
				rd.forward(request, response);
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
