package com.eshopping.service;

import java.util.List;

import com.eshopping.DAO.CustomerDAO;
import com.eshopping.DAO.CustomerDAOImpl;
import com.eshopping.model.CustomerDetails;
import com.eshopping.Exception.*;
public class CustomerServiceImpl implements CustomerService{

	
	CustomerDAO customerDAO=new CustomerDAOImpl();
	@Override
	public boolean userRgesitration(CustomerDetails customerDetails) {
	 
	List<CustomerDetails>allcustomerDetails=customerDAO.getAllCustomerDetials();
		boolean emailid=allcustomerDetails.stream().anyMatch((customer)->customer.getEmailid().equalsIgnoreCase(customerDetails.getEmailid()));
		if(emailid) {
			throw new CustomerException("Email Already Existed");
		}
		boolean mobilenumber=allcustomerDetails.stream().anyMatch((customer)->customer.getMobilenumber()==customerDetails.getMobilenumber());
		if(mobilenumber) {
			throw new CustomerException("Mobile_Number Already Existed");
		}
		boolean password=allcustomerDetails.stream().anyMatch((customer)->customer.getPassword().equals(customerDetails.getPassword()));
		if(password) {
			throw new CustomerException("Password Already Existed");
		}
       if(customerDAO.insertCustomerDetails(customerDetails)!=0) {
    	   return true;
       }
       return false;
	}

}
