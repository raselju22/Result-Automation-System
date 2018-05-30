package com.final_year.app.service;

import com.final_year.app.domain.Users;

public interface UsersService {

	public void addUsers(Users users);
	
	Users findUserByusername(String username);
}
