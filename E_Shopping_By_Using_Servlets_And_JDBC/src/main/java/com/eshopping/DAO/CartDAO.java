package com.eshopping.DAO;

import com.eshopping.model.*;
public interface CartDAO {
	int getCustomerId(int customerid);
	int insertCartDetails(Cart cart);
	boolean isProductInCart(int customerId, int productId);
}
