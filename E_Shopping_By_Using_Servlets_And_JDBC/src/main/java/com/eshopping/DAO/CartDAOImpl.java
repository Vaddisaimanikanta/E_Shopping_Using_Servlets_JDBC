package com.eshopping.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.eshopping.model.Cart;

public class CartDAOImpl implements CartDAO
{
	
	private static final String url =
			"jdbc:mysql://localhost:your_localhost/teca63_e_shopping?user=root&password=your_password";
	
	private static final String count_of_customer_id=
			"select count(Customer_Id) from cart where Customer_Id=? ";
	private static final String check_product_in_cart =
	        "SELECT COUNT(*) FROM cart WHERE Customer_Id = ? AND Product_Id = ?";

	private static final String insert=
			"insert into cart (Customer_Id,Product_Id) values (?,?)";

	@Override
	public int getCustomerId(int customerid) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(count_of_customer_id);
			preparedStatement.setInt(1, customerid);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				ResultSetMetaData metaData = resultSet.getMetaData();
				int columnCount=metaData.getColumnCount();
				System.out.println("Column count of Buffer memory   :"+columnCount);
				System.out.println("Column name in Buffer Memory    :"+metaData.getColumnLabel(1));
				return resultSet.getInt(1);
			}
			return 0;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public boolean isProductInCart(int customerId, int productId) {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection(url);
	        PreparedStatement preparedStatement = connection.prepareStatement(check_product_in_cart);
	        preparedStatement.setInt(1, customerId);
	        preparedStatement.setInt(2, productId);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            return resultSet.getInt(1) > 0; // Returns true if product exists
	        }
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}


	@Override
	public int insertCartDetails(Cart cart) {
		if (isProductInCart(cart.getCustomer_id(), cart.getProduct_id())) {
	        return 0; // Skip insert if product is already in the cart
	    }
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(insert);
			preparedStatement.setInt(1, cart.getCustomer_id());
			preparedStatement.setInt(2, cart.getProduct_id());
			return preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}

}
