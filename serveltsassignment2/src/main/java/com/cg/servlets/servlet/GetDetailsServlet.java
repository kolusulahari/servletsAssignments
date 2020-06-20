package com.cg.servlets.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.servlets.dao.Dao;
import com.cg.servlets.dao.DaoImpl;
import com.cg.servlets.dto.Dto;

@WebServlet("/GetDetails")
public class GetDetailsServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		Dao employee = new DaoImpl();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>All Employees</h1>");
		

		List<Dto> list = employee.getAllEmployeeDetail();

		out.print("<table border='1' width='100%'");
		out.print(
				"<tr><th>Id</th><th>Name</th><th>Age</th><th>Designation</th><th>Salary</th><th>Password</th></tr>");
		for (Dto e : list) {
			out.print("<tr><td>" + e.getId() + "</td><td>" + e.getName() + "</td><td>" + e.getAge() + "</td>" + " <td>"
					+ e.getDesignation() + "</td><td>" + e.getSalary() + "</td>" + "<td>"+ e.getPassword() +"</td>");
		}
		out.print("</table>");
		
		RequestDispatcher res = request.getRequestDispatcher("/form.html");
        res.include(request, response);  

		out.close();
	}
}