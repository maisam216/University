package com.pages;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 
 * Home page SERVLET
 * Get and Post methods
 * 
 */
public class Home extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public void destroy() {
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");
      RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
      rd.include(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      response.setContentType("text/html");
	      RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
	      rd.include(request, response);
	   }
}
