package com.cg.servlets.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cg.sevlets.services.Service;
import com.cg.sevlets.services.ServiceImpl;


@WebServlet("/DeleteServlet")  
public class DeleteServlet extends HttpServlet{
	
	Service service = new ServiceImpl();
	
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp)   
	             throws ServletException, IOException {  
	      
	    	String sid=req.getParameter("id");  
	        
	    	int id=Integer.parseInt(sid);  
	        			
			boolean deleted = service.deleteEmployeeInfo(id);
	        
	        PrintWriter out = resp.getWriter();
			
			out.print("<html>");
			out.print("<body>");
			
			if(deleted == true) {
				out.print("<h2 style = 'color:green'>Employee deleted successfully.....</h2>");
			} else {
				out.print("<h2 style = 'color:red'>Employee not deleted!!!!</h2>");
			}
			
	  	    RequestDispatcher res = req.getRequestDispatcher("/form.html");
		    res.include(req, resp);  
			
		    out.print("</body>");
			out.print("</html>");
	      
	    }  

}