package com.company;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException{
		response.getWriter().println("Hello, World!");
	}
	@Override
	public void init() throws ServletException
	{
		System.out.println("Servlet " + this.getServletName() + " has started.");
	}
	@Override
	public void destroy()
	{
		System.out.println("Servlet " + this.getServletName() + " has stopped.");
	}
}
