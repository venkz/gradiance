package ncsu.csc.db.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	private void redirector(int role, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		CourseManager cm;
		try {
			cm = new CourseManager();
			request.setAttribute("CourseList",cm.GetCourseList(session.getAttribute("Session_UserName").toString(), role));
			if(role==0)
			{
				request.setAttribute("Rolename","Student");
				RequestDispatcher rd = request.getRequestDispatcher("LandingPage.jsp");
				rd.forward(request, response);
				return;
			}
			else {
				request.setAttribute("Rolename","Professor");
				RequestDispatcher rd = request.getRequestDispatcher("CreateUser.jsp");
				rd.forward(request, response);
				return;	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String logout = request.getParameter("logout");
		String notifications = request.getParameter("notifications");
		String deleteNotif = request.getParameter("deleteNotif");
		
		if(logout != null) {
			HttpSession session = request.getSession(false);
			session.invalidate();
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
			return;	
			
		} else if (notifications != null) {
			UsersManager um;
			try {
				um = new UsersManager();
				HttpSession session = request.getSession(false);
				request.setAttribute("notifications",um.GetNotifications(session.getAttribute("Session_UserName").toString()));
				RequestDispatcher rd = request.getRequestDispatcher("Notifications.jsp");
				rd.forward(request, response);
				return;	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (deleteNotif != null) {
			UsersManager um;
			try {
				um = new UsersManager();
				HttpSession session = request.getSession(false);
				um.deleteNotifications(session.getAttribute("Session_UserName").toString());
				int role = Integer.parseInt(session.getAttribute("Session_UserRole").toString());
				redirector(role, request, response, session);
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			try {
				UsersManager userMan = new UsersManager();
				Users users = new Users();
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				users.setUserName	(username);
				users.setPassword(password);
				int role = userMan.validateLogin(users);
				
				if(role == -1) {
					request.setAttribute("errormsg", "Invalid login details!");
					request.setAttribute("text", "Try again");
					RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
					rd.forward(request, response);
					return;
				} else {
					
					userMan.recordLogin(username);
					
					HttpSession session = request.getSession();
					request.setAttribute("Username", username);
					session.setAttribute("Session_UserName", username);
					session.setAttribute("Session_UserRole", role);
					
					redirector(role, request, response, session);
					return;
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
