package com.eshopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eshopping.DAO.CartDAO;
import com.eshopping.DAO.CartDAOImpl;
import com.eshopping.DAO.CustomerDAO;
import com.eshopping.DAO.CustomerDAOImpl;
import com.eshopping.DAO.ProductDAO;
import com.eshopping.DAO.ProductDAOImpl;
import com.eshopping.model.CustomerDetails;
import com.eshopping.model.ProductDetails;

@WebServlet("/userlogin")
public class UserLogin extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailid = request.getParameter("emailid");
		String password = request.getParameter("password");
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");
		ProductDAO productDAO=new ProductDAOImpl();
		CustomerDAO customerDAO = new CustomerDAOImpl();
		CartDAO cartDAO = new CartDAOImpl();
		CustomerDetails customerDetails=customerDAO.getCustomerDetailsBasedOnEmailIdAndPassword(emailid, password);
	    List<ProductDetails>allProductDetails=productDAO.getAllProductDetails();
		 HttpSession session=request.getSession();
		 int count =cartDAO.getCustomerId(customerDetails.getId());
		if(customerDetails!=null) {
			session.setAttribute("logincustomerdetails", customerDetails);
			session.setAttribute("cartcount", count);
			session.setAttribute("allproductdetails", allProductDetails);
			RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayAllProducts.jsp");
			dispatcher.forward(request, response);
		}
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("UserLogin.html");
			dispatcher.include(request, response);
			writer.println("<center><h2>Invalid Details</h2></center>");
		}
	}
}
