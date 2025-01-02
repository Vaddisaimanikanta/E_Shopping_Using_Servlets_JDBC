package com.eshopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetails {
//id, Name, Brand, Price, Discount, Category, Quantity
   private int id;
   private  String name;
   private String brand;
   private double price;
   private double discount;
   private String Category;
   private int Quantity;
   
}
