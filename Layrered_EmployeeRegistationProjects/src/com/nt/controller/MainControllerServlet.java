package com.nt.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dto.EmployeeDTO;
import com.nt.services.EmployeeMgtServiceImpl;
import com.nt.services.EmplyeeMgmtService;
import com.nt.vo.EmployeeVO;

@WebServlet( "/controller")
public class MainControllerServlet extends HttpServlet {
	private EmplyeeMgmtService service;
		@Override
		public void init() throws ServletException{
			service = new EmployeeMgtServiceImpl();
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=null,addrs=null,doj=null,basicSalary=null;
		EmployeeVO vo=null;
		EmployeeDTO dto=null;
		PrintWriter pw=null;
		String result=null;
		//read form data and store in VO class object
		vo= new EmployeeVO();
		vo.setEname(request.getParameter("ename"));
		vo.setEadd(request.getParameter("eadd"));
		vo.setDoj(request.getParameter("doj"));
		System.out.println(request.getParameter("doj"));
		vo.setBasicSalary(request.getParameter("basicSalary"));
		//create PrintWriter
		pw=response.getWriter();
		response.setContentType("text/html");
		//convert VO class objeact
		dto=new EmployeeDTO();
		dto.setEname(vo.getEname());
		dto.setEadd(vo.getEadd());
		dto.setDoj(java.sql.Date.valueOf(vo.getDoj()));
		dto.setBasicSalary(Float.parseFloat(vo.getBasicSalary()));
		
		//use service class
		try {
			result=service.registerEmployee(dto);
			pw.println("<h1 style='color:green;text-align:center'>Result::"+result+"</h1>");
			
		}
		catch(Exception e) {
			pw.println("<h1 style='color:red;text-align:center'>Internal Problem ---Try again</h1>");
			e.printStackTrace();
		}
		
		//add hyperlink
		pw.println("<br><br><a href='employee_register.html'>home</a>");
		//close stream
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	@Override
	public void destroy() {
		service=null;
	}

}
