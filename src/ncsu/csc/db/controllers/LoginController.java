package ncsu.csc.db.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ncsu.csc.db.beans.Users;
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
				request.setAttribute("success", role);
				RequestDispatcher rd = request.getRequestDispatcher("LandingPage.jsp");
				rd.forward(request, response);
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
