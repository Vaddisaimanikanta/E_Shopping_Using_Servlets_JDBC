package com.eshopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshopping.DAO.CustomerDAO;
import com.eshopping.DAO.CustomerDAOImpl;
import com.eshopping.Exception.CustomerException;
import com.eshopping.model.CustomerDetails;
import com.eshopping.service.CustomerService;
import com.eshopping.service.CustomerServiceImpl;
@WebServlet("/register")
public class CustomerRegistration extends HttpServlet{
 @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	 String name=request.getParameter("name");
	 String emailid=request.getParameter("emailid");
	 long number=Long.parseLong(request.getParameter("mobilenumber"));
	 String password=request.getParameter("pass");
	 String gender=request.getParameter("gen");
	 String address=request.getParameter("address");
	 CustomerDetails customerDetails=new CustomerDetails();
	 customerDetails.setName(name);
	 customerDetails.setEmailid(emailid);
	 customerDetails.setMobilenumber(number);
	 customerDetails.setPassword(password);
	 customerDetails.setGender(gender);
	 customerDetails.setAddress(address);
	 PrintWriter writer=response.getWriter();
	 CustomerService customerService=new CustomerServiceImpl();
	 try {
	 if(customerService.userRgesitration(customerDetails)) {

			RequestDispatcher dispatcher=request.getRequestDispatcher("UserLogin.html");
			dispatcher.forward(request, response);
	 }
	 else {
		 RequestDispatcher dispatcher=request.getRequestDispatcher("CustomerRegistration.html");
		 dispatcher.include(request, response);
		 writer.println("<center><h2>Invalid Data</h2></center>");
	 }
	 }
	 catch ( CustomerException e) {
		 RequestDispatcher dispatcher=request.getRequestDispatcher("CustomerRegistration.html");
		 dispatcher.include(request, response);
		 writer.println("<center><h5>"+e.getMsg()+"</h5></center>");
	
	}
	 
}
}
