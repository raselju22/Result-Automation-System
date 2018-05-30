package com.final_year.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.final_year.app.dao.UsersDao;
import com.final_year.app.domain.Users;
import com.final_year.app.service.UsersService;


@Service

public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersDao usersDao;
	
	@Transactional()
	
	public void addUsers(Users users) {
		
		usersDao.save(users);
		
	}

	@Transactional(readOnly = true)
	
	public Users findUserByusername(String username) {
		
		return usersDao.findUserByusername(username);
	}

}
