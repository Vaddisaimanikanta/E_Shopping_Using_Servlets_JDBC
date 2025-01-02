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
<title>Insert title here</title>
<style>

body {
    font-family: Arial, sans-serif;
    background-color: lightblue;
    margin: 0;
    padding: 0;
}
h2 {
    color: #333;
    margin-top: 20px;
}
form {
    margin-bottom: 20px;
}
form input {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 14px;
    width: 300px;
}
form button {
    background-color: #007BFF;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 14px;
}
form button:hover {
    background-color: #0056b3;
}
a button {
    text-decoration: none;
    padding: 10px 15px;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 14px;
    cursor: pointer;
}
a button:hover {
    background-color: darkgreen;
}
table {
    width: 80%;
    margin: 0 auto;
    border-collapse: collapse;
    margin-top: 20px;
    background-color: white;
}
th, td {
    border: 1px solid #ccc;
    text-align: center;
    padding: 10px;
    font-size: 14px;
}
th {
    background-color: #f8f9fa;
    color: red;
}
td button {
    background-color: black;
    color: white;
    padding: 5px 10px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}
td button:hover {
    background-color: #444;
}

</style>
</head>
<body style="background: lightblue;">
<%

ProductDAO dao = new ProductDAOImpl();
List<ProductDetails> allProductDetails = dao.getAllProductDetails();
%>
<center><h2>All Product Details</h2>

<form action=" ">Filter
<input placeholder="Search By Name,Brand,Category"><button>SEARCH</button>
</form>
<br>
<a href="AddProducts.html"><button style="background: green;">Add Products</button></a>
<table border=" ">
<tr>
<th style="color: red;">Product Name</th>
<th style="color: red;">Product Price</th>
<th style="color: red;">Product Brand</th>
<th style="color: red;">Product Discount</th>
<th style="color: red;">Product Category</th>
<th style="color: red;">Product Quantity</th>
<th style="color: red;">Action</th>
</tr>

<%
for(ProductDetails productDetails:allProductDetails)
{%>
	<tr>
	<td><%=productDetails.getName() %></td>
	<td><%=productDetails.getPrice() %></td>
	<td><%=productDetails.getBrand() %></td>
	<td><%=productDetails.getDiscount() %></td>
	<td><%=productDetails.getCategory() %></td>
	<td><%=productDetails.getQuantity() %></td>
	<td><button style="background:black;color:white;">Update</button>
	<button style="background:black;color:white;">Delete</button>
	</td>
	</tr>
<%}%>

</table>
</center>
</body>
</html>