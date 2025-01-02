package com.eshopping.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eshopping.model.ProductDetails;

public class ProductDAOImpl implements ProductDAO
{
	private static final String url = "jdbc:mysql://localhost:your_localhost/"
			+ "teca63_e_shopping?user=root&password=your_password";
	private static final String insert = "insert into product_details "
			+ "(Name, Brand, Price, Discount, Category, Quantity) values (?,?,?,?,?,?)";
	private static final String select_all = "select * from product_details";
	
	private static final String pro_select_all="select * from product_details where id=? ";

	@Override
	public int insertProductDetails(ProductDetails productDetails) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement  preparedStatement = connection.prepareStatement(insert);
			preparedStatement.setString(1, productDetails.getName());
			preparedStatement.setString(2, productDetails.getBrand());
			preparedStatement.setDouble(3, productDetails.getPrice());
			preparedStatement.setDouble(4, productDetails.getDiscount());
			preparedStatement.setString(5, productDetails.getCategory());
			preparedStatement.setInt(6,productDetails.getQuantity());
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block*
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<ProductDetails> getAllProductDetails() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(select_all);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			List<ProductDetails> allProductDetails = new ArrayList<ProductDetails>();
			
			if(resultSet.isBeforeFirst())
			{
				while(resultSet.next())
				{
					ProductDetails productDetails = new ProductDetails();
					productDetails.setId(resultSet.getInt("id"));
					productDetails.setName(resultSet.getString("Name"));
					productDetails.setBrand(resultSet.getString("Brand"));
					productDetails.setPrice(resultSet.getDouble("Price"));
					productDetails.setDiscount(resultSet.getDouble("Discount"));
					productDetails.setCategory(resultSet.getString("Category"));
					productDetails.setQuantity(resultSet.getInt("Quantity"));
					
					allProductDetails.add(productDetails);
					
				}
				return allProductDetails;
			}
			else
			{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public ProductDetails getProductDetailsByid(int productid) {
	    ProductDetails productDetails=new ProductDetails();
	    try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(pro_select_all);
			preparedStatement.setInt(1, productid);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				productDetails.setId(resultSet.getInt("id"));
				productDetails.setName(resultSet.getString("Name"));
				productDetails.setBrand(resultSet.getString("Brand"));
				productDetails.setDiscount(resultSet.getDouble("Discount"));
				productDetails.setPrice(resultSet.getDouble("Price")); //Quantity
				productDetails.setQuantity(resultSet.getInt("Quantity"));
			}
			return productDetails;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}

	
}
