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


@SuppressWarnings("serial")
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Dao employee = new DaoImpl();
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.println("<h1>Employee Details</h1>");

		String sid = req.getParameter("id");

		int id = Integer.parseInt(sid);

		Dto bean = employee.getEmployeeDetailById(id);

		out.print("<table border='1' width='100%'");
		out.print("<tr><th>Id</th><th>Name</th><th>Age</th><th>Designation</th><th>Salary</th><th>Password</th></tr>");
		if (bean != null) {
			out.print("<tr><td>" + bean.getId() + "</td><td>" + bean.getName() + "</td><td>" + bean.getAge() + "</td>"
					+ " <td>" + bean.getDesignation() + "</td><td>" + bean.getSalary() + "</td>" + "<td>"
					+ bean.getPassword() + "</td>");
		}
		out.print("</table>");

		RequestDispatcher res = req.getRequestDispatcher("/form.html");
		res.include(req, resp);

		out.close();
	}

}