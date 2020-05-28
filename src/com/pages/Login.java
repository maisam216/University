package com.pages;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.main.DbHandler;

/*
 * 
 * Login page SERVLET
 * Get and Post methods
 * 
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		if(session.getAttribute("session")!=null) {
			response.sendRedirect("Admin");
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		//Configure database
		String[] dbConfig = new String[3];
		dbConfig[0] = getServletContext().getInitParameter("dbhost");
		dbConfig[1] = getServletContext().getInitParameter("dbusername");
		dbConfig[2] = getServletContext().getInitParameter("dbpassword");
		DbHandler dbHandler = new DbHandler(dbConfig[0],dbConfig[1],dbConfig[2]);

		PrintWriter writer=response.getWriter();
		String username = request.getParameter("un");
		String password = request.getParameter("ps");

		try {
			dbHandler.connect();
			if (dbHandler.loginValidate(username, password) == 1) {
				HttpSession session = request.getSession(true);
				session.setAttribute("username", username);
				session.setAttribute("password", password);
				session.setAttribute("session", true);
				RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
				rd.include(request, response);
			} else if (dbHandler.loginValidate(username, password) == 0) {
				writer.append("loginNotMatch");
			} else if (dbHandler.loginValidate(username, password) == -1) {
				writer.append("loginError");
			} 
			dbHandler.disconnect();
		} catch (Exception e) {
			writer.append("loginError");
		}
	}
}
