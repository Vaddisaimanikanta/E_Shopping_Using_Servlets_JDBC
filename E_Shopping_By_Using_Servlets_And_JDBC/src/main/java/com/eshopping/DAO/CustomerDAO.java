package com.eshopping.DAO;

import java.util.List;

import com.eshopping.model.CustomerDetails;

public interface CustomerDAO {
  int insertCustomerDetails(CustomerDetails customerDetails);
  List<CustomerDetails> getAllCustomerDetials();
  CustomerDetails getCustomerDetailsBasedOnEmailIdAndPassword(String emailid,String password);

}
