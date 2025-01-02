package com.eshopping.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eshopping.model.CustomerDetails;

public class CustomerDAOImpl implements CustomerDAO{
    private static final String url="jdbc:mysql://localhost:your_localhost/teca63_e_shopping?user=root&password=your_password";
    private static final String insert=
    		"insert into customer_details(Name, Emailid, Password, Mobile_Number, Gender, Address) values (?,?,?,?,?,?)";
    private static final String select="select * from customer_details";
    private static final String select_based_on_emailid_and_password =
    		"select * from customer_details where emailid=? and password=?";
	@Override
	public int insertCustomerDetails(CustomerDetails customerDetails) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(insert);
			preparedStatement.setString(1, customerDetails.getName());
			preparedStatement.setString(2, customerDetails.getEmailid());
			preparedStatement.setString(3, customerDetails.getPassword());
			preparedStatement.setLong(4, customerDetails.getMobilenumber());
			preparedStatement.setString(5, customerDetails.getGender());
			preparedStatement.setString(6, customerDetails.getAddress());
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
	@Override
	public List<CustomerDetails> getAllCustomerDetials() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(select);
			ResultSet resultSet=preparedStatement.executeQuery();
			List<CustomerDetails> list=new ArrayList<CustomerDetails>();
			if(resultSet.isBeforeFirst()) {
				while(resultSet.next()) {
					CustomerDetails customerDetails=new CustomerDetails();
					customerDetails.setEmailid(resultSet.getString("Emailid"));
					customerDetails.setMobilenumber(resultSet.getLong("Mobile_Number"));
					customerDetails.setPassword(resultSet.getString("Password"));
					list.add(customerDetails);
					
				}
				return list;
			}
			else {
				return null;
			}
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
	@Override
	public CustomerDetails getCustomerDetailsBasedOnEmailIdAndPassword(String emailid, String password) {
		// TODO Auto-generated method stub
		CustomerDetails customerDetails=new CustomerDetails();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(select_based_on_emailid_and_password);
			preparedStatement.setString(1, emailid); // Set emailid parameter
	        preparedStatement.setString(2, password); // Set password parameter
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				    customerDetails = new CustomerDetails();
					customerDetails.setEmailid(resultSet.getString("Emailid"));
					customerDetails.setMobilenumber(resultSet.getLong("Mobile_Number"));
					customerDetails.setPassword(resultSet.getString("Password"));
					customerDetails.setName(resultSet.getString("Name"));
					customerDetails.setId(resultSet.getInt("id"));		
			}		
			return customerDetails;
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
