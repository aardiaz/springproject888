package com.bway.springproject.service;

import com.bway.springproject.model.User;

public interface UserService {

	void signup(User user);
	
	User login(String un, String psw);
	
}
