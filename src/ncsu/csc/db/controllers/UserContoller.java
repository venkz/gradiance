package ncsu.csc.db.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ncsu.csc.db.beans.Enrollments;
import ncsu.csc.db.beans.Users;
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
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UsersManager userMan = new UsersManager();
			String method = request.getParameter("func");
			
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
				Enrollments enrollments = new Enrollments();
				if(request.getParameter("username") == null) {
					enrollments.setUsername(request.getParameter("username"));
				} else {
					enrollments.setUsername(request.getParameter("username"));
				}
				
				enrollments.setToken(request.getParameter("token"));
				
				if(request.getParameter("ista") == null) {
					enrollments.setIsta(0);
				} else {
					enrollments.setIsta(Integer.parseInt(request.getParameter("ista")));
				}
				enrollments.setIsta(Integer.parseInt(request.getParameter("ista")));
				
				
				int created = userMan.enrollUser(enrollments);
				if(created > 0) {
					RequestDispatcher rd = request.getRequestDispatcher("CreateUser.jsp");
					rd.forward(request, response);
					return;
				} else {
					request.setAttribute("errormsg", "Unalbe to enroll user!");
					request.setAttribute("link", "CreateUser.jsp");
					request.setAttribute("text", "Go Back");
					RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
					rd.forward(request, response);
					return;
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
