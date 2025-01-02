package com.eshopping.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.eshopping.controller.AdminLogin;
import com.eshopping.model.Admin;

public class AdminDAOImpl implements AdminDAO
{

	private static final String url = 
			"jdbc:mysql://localhost:your_localhost/teca63_e_shopping?user=root&password=your_password";
	private static final String getAdminDetails =
			"select * from admin where  admin_emailid=? and admin_password=?";
	@Override
	public boolean getAdminDetails(String emailid, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(getAdminDetails);
			preparedStatement.setString(1, emailid);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
				if(resultSet.next()) {
					return true;
				}else {
			        return false;
				}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

}
