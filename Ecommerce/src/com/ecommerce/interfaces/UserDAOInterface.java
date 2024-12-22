package com.ecommerce.interfaces;

import com.ecommerce.model.User;

public interface UserDAOInterface {

	void registerUsers(User user);
	
	boolean loginUser(String username,String password);
}
