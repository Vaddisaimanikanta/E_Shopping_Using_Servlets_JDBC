package com.eshopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Setter
//@Getter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Data
public class CustomerDetails {
   private int id;
   private String name;
   private String emailid;
   private String password;
   private long mobilenumber;
   private String gender;
   private String address;
}
