package com.eshopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshopping.DAO.ProductDAO;
import com.eshopping.DAO.ProductDAOImpl;
import com.eshopping.model.ProductDetails;

@WebServlet("/addproducts")
public class AddProductsDetails extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String category = request.getParameter("category");
		String brand = request.getParameter("brand");
		String name = request.getParameter("productname");
		String tempprice = request.getParameter("price");
		double price = Integer.parseInt(tempprice);
		String tempquantity = request.getParameter("quantity");
		int quantity = Integer.parseInt(tempquantity);
		String tempdiscount = request.getParameter("discount");
		double discount = Double.parseDouble(tempdiscount);
		ProductDetails productDetails = new ProductDetails();
		productDetails.setName(name);
		productDetails.setBrand(brand);
		productDetails.setCategory(category);
		productDetails.setPrice(price);
		productDetails.setDiscount(discount);
		productDetails.setQuantity(quantity);
		System.out.println(productDetails);
		
		ProductDAO productDAO = new ProductDAOImpl();
		PrintWriter writer = response.getWriter();
		
		if(productDAO.insertProductDetails(productDetails)>0)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("AllProductDetails.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("AddProducts.html");
			dispatcher.include(request, response);
			writer.println("Server Error 404");
		}
			
		
	}
}
