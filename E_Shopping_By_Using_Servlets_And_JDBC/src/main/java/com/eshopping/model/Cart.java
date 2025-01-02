package com.eshopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
//cart_id, Customer_Id, Product_Id
	
	private int cart_id;
	private int customer_id;
	private int product_id;
}
