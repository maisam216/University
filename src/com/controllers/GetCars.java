package com.controllers;

import com.main.VehicleReportData;
import com.main.DbHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetCars extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String CONTENT_TYPE = "application/json";
	public static final String CHARACTER_ENCODING = "UTF-8";

	//Do Post method
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		response.setCharacterEncoding(CHARACTER_ENCODING);

		//Get database keys from web.xml
		String[] dbConfig = new String[3];
		dbConfig[0] = getServletContext().getInitParameter("dbhost");
		dbConfig[1] = getServletContext().getInitParameter("dbusername");
		dbConfig[2] = getServletContext().getInitParameter("dbpassword");
		DbHandler dbHandler = new DbHandler(dbConfig[0],dbConfig[1],dbConfig[2]);

		PrintWriter writer = response.getWriter();
		StringBuffer jb = new StringBuffer();
		ArrayList <VehicleReportData> cars = new ArrayList <VehicleReportData> (); 
		
		//Read data from the client side as JSON
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {e.printStackTrace();}

		JSONObject obj = new JSONObject(jb.toString());
		JSONArray data = obj.getJSONArray("Car");

		//bring data from database
		try {
			dbHandler.connect();
			final int n = data.length();
			for (int i = 0; i < n; ++i) {

				final JSONObject jPost = data.getJSONObject(i);
				// RETRIEVE EACH JSON OBJECT'S FIELDS
				int year = jPost.getInt("year");
				String make = jPost.getString("make");
				String model = jPost.getString("model");
				String version = jPost.getString("version");
				String variant = jPost.getString("variant");
				String fuel = jPost.getString("fuel");

				// CONVERT DATA FIELDS TO CLUB OBJECT
				VehicleReportData car =  dbHandler.getCarByDetails(year, make, model, version, variant, fuel);
				cars.add(car);

			}
			dbHandler.disconnect();
		} catch (Exception e) {e.printStackTrace();}

		//Send data to the client
		JSONObject json = new JSONObject();
		json.put("Car", cars);
		writer.append(json.toString());
	}
}
