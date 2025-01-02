package com.eshopping.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
//Order_id, Customer_Id, Product_Id, Purchase_Quantity, Purchase_Date
	 int order_id;
	private int customer_id;
	private int product_id;
	private int purchase_quantity;
	private Date purchase_date;
}
