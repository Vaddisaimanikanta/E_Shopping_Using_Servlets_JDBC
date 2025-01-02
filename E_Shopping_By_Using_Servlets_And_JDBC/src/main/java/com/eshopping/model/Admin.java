package com.eshopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
//admin_id, admin_emailid, admin_password
	private int id;
	private String emailid;
	private String password;
}
