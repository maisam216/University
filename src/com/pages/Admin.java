/*
 * Maisam Alatrach 20091817
 * Oudai Fayek 20091861
 * Latrobe University
 * 29/05/2020
 */
package com.pages;

import java.io.IOException;

/*
 * 
 * admin page SERVLET
 * Get and Post methods
 * 
 */

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Admin extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public void destroy() {
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");
      if(request.getSession().getAttribute("session")!=null) {
    	  RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
          rd.include(request, response);
      }else {
    	  RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
          rd.include(request, response);
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      response.setContentType("text/html");
	      if(request.getSession().getAttribute("session")!=null) {
	    	  RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
	          rd.include(request, response);
	      }else {
	    	  RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
	          rd.include(request, response);
	      }
	   }
}
