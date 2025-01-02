package com.eshopping.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshopping.DAO.CartDAO;
import com.eshopping.DAO.CartDAOImpl;
import com.eshopping.model.Cart;

@WebServlet("/addtocart")
public class AddToCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productid = Integer.parseInt(request.getParameter("productid"));
        int customerid = Integer.parseInt(request.getParameter("customerid"));

        Cart cart = new Cart();
        cart.setCustomer_id(customerid);
        cart.setProduct_id(productid);

        CartDAO cartDAO = new CartDAOImpl();

        // Check if the product already exists in the cart
        if (cartDAO.isProductInCart(customerid, productid)) {
            // If the product is already in the cart, notify the user
            request.setAttribute("message", "Product is already in your cart!");
        } else {
            // If the product is not in the cart, insert it
            if (cartDAO.insertCartDetails(cart) > 0) {
                int countCart = cartDAO.getCustomerId(customerid);
                request.getSession().setAttribute("cartcount", countCart);
                request.setAttribute("message", "Product added to cart successfully!");
            } else {
                request.setAttribute("message", "Failed to add product to cart. Please try again.");
            }
        }

        // Forward the request to the same page with a message
        RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayAllProducts.jsp");
        dispatcher.forward(request, response);
    }
}
