/*
 * Maisam Alatrach 20091817
 * Oudai Fayek 20091861
 * Latrobe University
 * 29/05/2020
 */

package com.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.main.DbHandler;

//This class is  with filters drop boxes 
public class DropBoxes extends HttpServlet {

	//Servlet settings
	private static final long serialVersionUID = 1L;
	public static final String CONTENT_TYPE = "application/json";
	public static final String CHARACTER_ENCODING = "UTF-8";

	//Do post method
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		response.setCharacterEncoding(CHARACTER_ENCODING);
		
		//Get database keys from web.xml
		String[] dbConfig = new String[3];
		dbConfig[0] = getServletContext().getInitParameter("dbhost");
		dbConfig[1] = getServletContext().getInitParameter("dbusername");
		dbConfig[2] = getServletContext().getInitParameter("dbpassword");
		
		//Start connection with data base
		DbHandler dbHandler = new DbHandler(dbConfig[0],dbConfig[1],dbConfig[2]);
		
		//Read JSON data from the client 
		PrintWriter writer = response.getWriter();
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {e.printStackTrace();}

		//Extract Data from JSON object
		JSONObject obj = new JSONObject(jb.toString());
		String req = obj.get("req").toString();
		int year =  Integer.parseInt(obj.get("year").toString());
		String make =  obj.get("make").toString();
		String model =  obj.get("model").toString();
		String version =  obj.get("version").toString();
		String variant =   obj.get("variant").toString();

		JSONObject json = new JSONObject();
		ArrayList <String> OptionsList = new ArrayList <String> ();

		//Bring data from database
		try {
			dbHandler.connect();
			if (req.equals("getYears")) {
				OptionsList = dbHandler.getAllYears();
			}else if (req.equals("getMakes")) {
				OptionsList = dbHandler.getAllMakes(year);
			}else if (req.equals("getModels")) {
				OptionsList = dbHandler.getAllModels(year, make);
			}else if (req.equals("getVersions")) {
				OptionsList = dbHandler.getAllModelReleaseVersion(year, make, model);
			}else if (req.equals("getVariants")) {
				OptionsList = dbHandler.getAllVariant(year, make, model, version);
			}else if (req.equals("getFuels")) {
				OptionsList = dbHandler.getAllFuelTypeDesc(year, make, model, version, variant);
			}else {
				OptionsList.add("");
			}
			dbHandler.disconnect();
		} catch (Exception e) {e.printStackTrace();}

		//Send data via JSON to the client
		json.put("OptionsList", OptionsList);
		writer.append(json.toString());
	}
}
