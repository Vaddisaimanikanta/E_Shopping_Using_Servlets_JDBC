package com.eshopping.DAO;

import java.util.List;

import com.eshopping.model.ProductDetails;

public interface ProductDAO {
	int insertProductDetails(ProductDetails productDetails);
	List<ProductDetails> getAllProductDetails();
	ProductDetails getProductDetailsByid(int productid);
}
