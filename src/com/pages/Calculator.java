package com.pages;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 
 * Calculator page SERVLET
 * Get and Post methods
 * 
 */
public class Calculator extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");
      RequestDispatcher rd = request.getRequestDispatcher("calculator.jsp");
      rd.include(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");
      RequestDispatcher rd = request.getRequestDispatcher("calculator.jsp");
      rd.include(request, response);
   }
}