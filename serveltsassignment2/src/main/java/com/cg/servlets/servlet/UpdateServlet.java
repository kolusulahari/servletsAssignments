package com.cg.servlets.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.servlets.dto.Dto;
import com.cg.sevlets.services.Service;
import com.cg.sevlets.services.ServiceImpl;



@WebServlet("/EditEmployee")
public class UpdateServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Dto bean = new Dto();
		Service service = new ServiceImpl();

		String id = req.getParameter("id");
		String name = req.getParameter("name");
		
		bean.setId(Integer.parseInt(id));
		bean.setName(name);

		boolean check = service.updateEmployeeInfo(name);
		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>");

		if (check == true) {
			out.print("<h2 style = 'color:green'>Updated successfully.....</h2>");
		} else {
			out.print("<h2 style = 'color:red'>Updation not done!!!!!</h2>");
		}
		
		RequestDispatcher res = req.getRequestDispatcher("/form.html");
		res.include(req, resp);  
		
		out.println("</h1>");
		out.println("</body>");
		out.println("</html>");
	}

}