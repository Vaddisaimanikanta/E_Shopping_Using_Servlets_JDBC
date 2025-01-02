<%@page import="com.eshopping.model.ProductDetails"%>
<%@page import="com.eshopping.DAO.ProductDAOImpl"%>
<%@page import="com.eshopping.DAO.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Product Details</title>
    <style>
        /* General body styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        /* Form container styling */
        .form-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 500px;
            text-align: center;
        }

        /* Form heading styling */
        h1 {
            color: #333;
            margin-bottom: 20px;
        }

        /* Label styling */
        label {
            font-size: 14px;
            font-weight: bold;
            margin-bottom: 5px;
            display: block;
            color: #333;
        }

        /* Input fields styling */
        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
            font-size: 14px;
        }

        /* Styling for readonly fields */
        input[type="text"]:readonly, input[type="number"]:readonly {
            background-color: #f0f0f0;
            cursor: not-allowed;
        }

        /* Total price field styling */
        input[type="text"]#totalPrice {
            font-weight: bold;
            color: #333;
            background-color: #e0e0e0;
        }

        /* Submit button styling */
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
            font-size: 16px;
            margin-top: 10px;
            transition: background-color 0.3s ease;
        }

        /* Hover effect for submit button */
        input[type="submit"]:hover {
            background-color: #45a049;
        }

        /* Error message styling */
        .error {
            color: red;
            font-size: 14px;
            margin-top: 10px;
        }

        /* Form field input size for quantity */
        input[type="number"] {
            width: 50%;
        }

        /* Ensuring the form fields are aligned properly */
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        /* Styling for the input values */
        input[type="text"], input[type="number"] {
            font-size: 14px;
        }
    </style>
    <script>
        function calculateTotalPrice() {
            // Get price, discount, and quantity from the inputs
            const price = parseFloat(document.getElementById('price').value);
            const discount = parseFloat(document.getElementById('discount').value);
            const quantity = parseInt(document.getElementById('quantity').value) || 0;

            // Calculate the total price
            const totalPrice = (price * quantity) - (discount * quantity);

            // Update the total price input
            document.getElementById('totalPrice').value = totalPrice.toFixed(2);
        }
    </script>
</head>
<body>
<%
    String productId = request.getParameter("productid");
    ProductDetails productDetails = null;

    if (productId != null) {
        try {
            ProductDAO productDAO = new ProductDAOImpl();
            productDetails = productDAO.getProductDetailsByid(Integer.parseInt(productId));
        } catch (Exception e) {
            out.println("<p class='error'>Error fetching product details: " + e.getMessage() + "</p>");
        }
    }
%>
<div class="form-container">
    <h1>Product Details</h1>
    <%
        if (productDetails != null) {
    %>
        <form>
            <label for="name">Product Name</label>
            <input type="text" id="name" value="<%=productDetails.getName() %>" readonly><br>

            <label for="brand">Brand</label>
            <input type="text" id="brand" value="<%=productDetails.getBrand() %>" readonly><br>

            <label for="discount">Discount</label>
            <input type="text" id="discount" value="<%=productDetails.getDiscount() %>" readonly><br>

            <label for="price">Price</label>
            <input type="text" id="price" value="<%=productDetails.getPrice() %>" readonly><br>

            <label for="quantity">Quantity</label>
            <input type="number" id="quantity" value="1" min="1" oninput="calculateTotalPrice()"><br>

            <label for="totalPrice">Total Price</label>
            <input type="text" id="totalPrice" value="<%=productDetails.getPrice() - productDetails.getDiscount() %>" readonly><br>

        </form>
        <form>
         <a href="PaymentDetails.html"><input type="submit" value="Confirm"></a>
         <input type="button" value="Cancle">
        
        </form>
    <%
        } else {
    %>
        <p class="error">Product details not available.</p>
    <%
        }
    %>
</div>
</body>
</html>
