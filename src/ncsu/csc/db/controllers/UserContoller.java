package ncsu.csc.db.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ncsu.csc.db.beans.Enrollments;
import ncsu.csc.db.beans.Users;
import ncsu.csc.db.managers.CourseManager;
import ncsu.csc.db.managers.UsersManager;

/**
 * Servlet implementation class UserContoller
 */
public class UserContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserContoller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UsersManager userMan = new UsersManager();
			String method = request.getParameter("func");
			HttpSession session1=request.getSession(false);
			CourseManager cm1;
			
			if(method.equalsIgnoreCase("addUser")) {
				Users users = new Users();
				users.setUserName(request.getParameter("username"));
				users.setPassword(request.getParameter("password"));
				users.setFname(request.getParameter("fname"));
				users.setLname(request.getParameter("lname"));
				users.setEmail(request.getParameter("email"));
				users.setDegree(Integer.parseInt(request.getParameter("degree")));
				int created = userMan.insertUser(users);
				if(created > 0) {
					cm1 = new CourseManager();
					request.setAttribute("CourseList",cm1.GetCourseList(session1.getAttribute("Session_UserName").toString(), Integer.parseInt(session1.getAttribute("Session_UserRole").toString())));

					RequestDispatcher rd = request.getRequestDispatcher("CreateUser.jsp");
					rd.forward(request, response);
					return;
				} else {
					request.setAttribute("errormsg", "Unalbe to add user!");
					request.setAttribute("link", "CreateUser.jsp");
					request.setAttribute("text", "Go Back");
					RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
					rd.forward(request, response);
					return;
				}
			}
			else if(method.equalsIgnoreCase("addTA")) {
				HttpSession session = request.getSession(false);
				int isUser = Integer.parseInt(session.getAttribute("Session_UserRole").toString());
				
				Enrollments enrollments = new Enrollments();
				if(isUser == 0) {
					enrollments.setUsername(session.getAttribute("Session_UserName").toString());
				} else {
					enrollments.setUsername(request.getParameter("username"));
				}
				
				enrollments.setToken(request.getParameter("token"));
				
				if(isUser == 0) {
					enrollments.setIsta(0);
				} else {
					enrollments.setIsta(Integer.parseInt(request.getParameter("ista")));
				}
				
				String created = userMan.enrollUser(enrollments);
				CourseManager cm=new CourseManager();
				request.setAttribute("CourseList",cm.GetCourseList(session.getAttribute("Session_UserName").toString(), isUser));
				if(created.contains("Success")) {
					if(isUser == 0) {
						RequestDispatcher rd = request.getRequestDispatcher("LandingPage.jsp");
						rd.forward(request, response);
					}
					else {
						RequestDispatcher rd = request.getRequestDispatcher("CreateUser.jsp");
						rd.forward(request, response);
					}
					
					return;
				} else {
					request.setAttribute("errormsg", created);
					request.setAttribute("text", "Go Back");
					RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
					rd.forward(request, response);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
