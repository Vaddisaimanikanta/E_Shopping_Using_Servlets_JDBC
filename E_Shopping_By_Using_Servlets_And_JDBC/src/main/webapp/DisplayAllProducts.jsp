<%@page import="com.eshopping.model.CustomerDetails"%>
<%@page import="com.eshopping.DAO.CustomerDAOImpl"%>
<%@page import="com.eshopping.DAO.CustomerDAO"%>
<%@page import="com.eshopping.model.ProductDetails"%>
<%@page import="java.util.List"%>
<%@page import="com.eshopping.DAO.ProductDAOImpl"%>
<%@page import="com.eshopping.DAO.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>E-Commerce</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        h1, h2 {
            color: #333;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
        }
        .cart {
            padding: 10px 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
            margin: 10px 0;
            position: relative;
        }
        .cart:hover {
            background-color: #45a049;
        }
        .cart sup {
            position: absolute;
            top: -5px;
            right: -5px;
            background-color: red;
            color: white;
            font-size: 12px;
            padding: 3px 7px;
            border-radius: 50%;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        td {
            background-color: #fff;
        }
        form input[type="submit"] {
            padding: 8px 12px;
            background-color: #008CBA;
            color: white;
            border: none;
            cursor: pointer;
        }
        form input[type="submit"]:hover {
            background-color: #007bb5;
        }
        .buy {
            padding: 10px 15px;
            background-color: #f44336;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
            border-radius: 5px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            transition: background-color 0.3s ease;
        }
        .buy:hover {
            background-color: #e53935;
        }
        .message {
            margin: 10px 0;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
            color: #333;
        }
        .success {
            border-color: #4CAF50;
            background-color: #dff0d8;
            color: #3c763d;
        }
        .error {
            border-color: #f44336;
            background-color: #fddddd;
            color: #d9534f;
        }
    </style>
</head>
<body>
<%
    // Retrieve session attributes
    CustomerDetails customerDetails = (CustomerDetails) session.getAttribute("logincustomerdetails");
    List<ProductDetails> getAllProductDetails = (List<ProductDetails>) session.getAttribute("allproductdetails");
    Integer cartcount = (Integer) session.getAttribute("cartcount");
    cartcount = (cartcount == null) ? 0 : cartcount;

    // Retrieve message from the request scope
    String message = (String) request.getAttribute("message");
    String messageClass = message != null && message.contains("successfully") ? "success" : "error";
%>
<center>
<h1>All Product Details</h1>
<h2>Welcome, <%= customerDetails.getName() %></h2>
<button class="cart">My Cart <sup><%= cartcount %></sup></button>

<% if (message != null) { %>
    <div class="message <%= messageClass %>"><%= message %></div>
<% } %>

<table>
<tr>
    <th>Product Name</th>
    <th>Product Price</th>
    <th>Product Brand</th>
    <th>Product Discount</th>
    <th>Product Category</th>
    <th>Product Quantity</th>
    <th>Actions</th>
</tr>
<%
    for (ProductDetails productDetails : getAllProductDetails) {
%>
    <tr>
        <td><%= productDetails.getName() %></td>
        <td><%= productDetails.getPrice() %></td>
        <td><%= productDetails.getBrand() %></td>
        <td><%= productDetails.getDiscount() %></td>
        <td><%= productDetails.getCategory() %></td>
        <td><%= productDetails.getQuantity() %></td>
        <td>
            <form action="addtocart" method="get">
                <input type="hidden" name="productid" value="<%= productDetails.getId() %>">
                <input type="hidden" name="customerid" value="<%= customerDetails.getId() %>">
                <input type="submit" value="Add to Cart">
            </form>
        </td>
        <td>
            <a href="Buy.jsp?productid=<%= productDetails.getId() %>">
                <button class="buy">Buy</button>
            </a>
        </td>
    </tr>
<%
    }
%>
</table>
</center>
</body>
</html>
