package com.eshopping.model;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails {
//Payment_Id, Amount, Payment_Date, Payment_Time, Payment_Type, Customer_Id, Product_Id
	private  int Payment_Id;
	private double Amount;
	private Date Payment_Date;
	private Time Payment_Time;
	private String Payment_Type;
	private String Customer_Id;
	private String Product_Id;
}
