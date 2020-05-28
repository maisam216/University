package com.pages;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.main.DbHandler;
import com.main.Log;

/*
 * 
 * update password page SERVLET
 * Get and Post methods
 * 
 */
public class UpdatePass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdatePass() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		RequestDispatcher rd = request.getRequestDispatcher("Admin");
		rd.include(request, response);
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
		String pass = request.getParameter("pass");
		String confirm_pass = request.getParameter("confirm_pass");
		try {
			if(request.getSession().getAttribute("session")!=null && pass.contentEquals(confirm_pass) ) {
				dbHandler.connect();
				String username = (String) request.getSession().getAttribute("username");
				dbHandler.updatePass(username, pass);
				Log log = new Log(0,"password successfully updated", "Local Site", true);
				dbHandler.writeLog(log);
				dbHandler.disconnect();
				response.sendRedirect("Result");
			}else {
				response.sendRedirect("Admin");
			}
		} catch (Exception e) {
			Log log = new Log(0,e.toString(), "Local Site", false);
			dbHandler.writeLog(log);
			writer.append(e.toString());
		}
	}
}
