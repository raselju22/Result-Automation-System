package com.final_year.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.final_year.app.dao.AuthoritiesDao;
import com.final_year.app.domain.Authorities;
import com.final_year.app.service.AuthoritiesService;



@Service

public class AuthoritiesServiceImpl implements AuthoritiesService{

	@Autowired
	private AuthoritiesDao authoritiesDao;
	
	@Transactional()
	
	public void addAuthorities(Authorities authorities) {
		
		authoritiesDao.save(authorities);
	}
	@Transactional(readOnly = true)
	
	public Authorities findAuthoritiesByusername(String username) {
		
		return authoritiesDao.findAuthoritiesByusername(username);
	}

}
