/*
 * Maisam Alatrach 20091817
 * Oudai Fayek 20091861
 * Latrobe University
 * 29/05/2020
 */
package com.pages;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.main.DbHandler;
import com.main.GVG;
import com.main.Log;
import com.main.VehicleReportData;

/*
 * 
 * update GVG function SERVLET
 * Get and Post methods
 * 
 */
public class UpdateGVG extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateGVG() {
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

		String username = request.getParameter("un");
		String password = request.getParameter("ps");
		String date = request.getParameter("lu");
		
		String logDir = getServletContext().getRealPath("/");
		String separate = System.getProperty("file.separator");
		logDir =logDir + separate;

		try {
			if(request.getSession().getAttribute("session")!=null) {
				dbHandler.connect();
				GVG g = new GVG();
				ArrayList <VehicleReportData> cars = g.updateGVG(username, password, date, logDir);
				int nr = dbHandler.addVehicleReportDatas(cars);
				Log log = new Log(nr,"Successfully updated cars database", "GVG Site",true);//Don't change
				dbHandler.writeLog(log);
				dbHandler.disconnect();
				response.sendRedirect("Result");
			}else {
				response.sendRedirect("Admin");
			}
		} catch (Exception e) {
			Log log = new Log(0,e.toString(),"GVG Site",false);//Don't change
			dbHandler.writeLog(log);
			response.sendRedirect("Result");
		}
	}
}
